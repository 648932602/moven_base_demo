package com.modemo.javase.signature;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigProperties;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSignDesigner;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.util.Hex;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author moshengwei
 * @date 下午3:00年05月17日 下午3:00
 */
public class VisibleSignature implements SignatureInterface {

    private SignatureOptions signatureOptions;
    private PDVisibleSignDesigner visibleSignDesigner;
    private final PDVisibleSigProperties visibleSignatureProperties = new PDVisibleSigProperties();
    private boolean lateExternalSigning = false;

    public boolean isLateExternalSigning()
    {
        return lateExternalSigning;
    }

    // Find an existing signature (assumed to be empty). You will usually not need this.
    private PDSignature findExistingSignature(PDDocument doc, String sigFieldName)
    {
        PDSignature signature = null;
        PDSignatureField signatureField;
        PDAcroForm acroForm = doc.getDocumentCatalog().getAcroForm();
        if (acroForm != null)
        {
            signatureField = (PDSignatureField) acroForm.getField(sigFieldName);
            if (signatureField != null)
            {
                // retrieve signature dictionary
                signature = signatureField.getSignature();
                if (signature == null)
                {
                    signature = new PDSignature();
                    // after solving PDFBOX-3524
                    // signatureField.setValue(signature)
                    // until then:
                    signatureField.getCOSObject().setItem(COSName.V, signature);
                }
                else
                {
                    throw new IllegalStateException("The signature field " + sigFieldName + " is already signed.");
                }
            }
        }
        return signature;
    }

    /**
     * Set visible signature designer for a new signature field.
     *
     * @param filename
     * @param x position of the signature field
     * @param y position of the signature field
     * @param zoomPercent increase (positive value) or decrease (negative value) image with x percent.
     * @param imageStream input stream of an image.
     * @param page the signature should be placed on
     * @throws IOException
     */
    public void setVisibleSignDesigner(String filename, int x, int y, int zoomPercent,
                                       InputStream imageStream, int page)
            throws IOException
    {
        visibleSignDesigner = new PDVisibleSignDesigner(filename, imageStream, page);
        visibleSignDesigner.xAxis(x).yAxis(y).zoom(zoomPercent).adjustForRotation();
    }

    /**
     * Set visible signature properties for new signature fields.
     *
     * @param name
     * @param location
     * @param reason
     * @param preferredSize
     * @param page
     * @param visualSignEnabled
     */
    public void setVisibleSignatureProperties(String name, String location, String reason, int preferredSize,
                                              int page, boolean visualSignEnabled)
    {
        visibleSignatureProperties.signerName(name).signerLocation(location).signatureReason(reason).
                preferredSize(preferredSize).page(page).visualSignEnabled(visualSignEnabled).
                setPdVisibleSignature(visibleSignDesigner);
    }

    /**
     * Sign pdf file and create new file that ends with "_signed.pdf".
     *
     * @param inputFile The source pdf document file.
     * @param signedFile The file to be signed.
     * @param tsaUrl optional TSA url
     * @throws IOException
     */
    public void signPDF(File inputFile, File signedFile, String tsaUrl) throws IOException
    {
        this.signPDF(inputFile, signedFile, tsaUrl, null);
    }

    /**
     * Sign pdf file and create new file that ends with "_signed.pdf".
     *
     * @param inputFile The source pdf document file.
     * @param signedFile The file to be signed.
     * @param tsaUrl optional TSA url
     * @param signatureFieldName optional name of an existing (unsigned) signature field
     * @throws IOException
     */
    public void signPDF(File inputFile, File signedFile, String tsaUrl, String signatureFieldName) throws IOException
    {
        if (inputFile == null || !inputFile.exists())
        {
            throw new IOException("Document for signing does not exist");
        }

        setTsaUrl(tsaUrl);

        // creating output document and prepare the IO streams.

        try (FileOutputStream fos = new FileOutputStream(signedFile);
             PDDocument doc = PDDocument.load(inputFile))
        {
            int accessPermissions = SigUtils.getMDPPermission(doc);
            if (accessPermissions == 1)
            {
                throw new IllegalStateException("No changes to the document are permitted due to DocMDP transform parameters dictionary");
            }
            // Note that PDFBox has a bug that visual signing on certified files with permission 2
            // doesn't work properly, see PDFBOX-3699. As long as this issue is open, you may want to
            // be careful with such files.

            PDSignature signature;

            // sign a PDF with an existing empty signature, as created by the CreateEmptySignatureForm example.
            signature = findExistingSignature(doc, signatureFieldName);

            if (signature == null)
            {
                // create signature dictionary
                signature = new PDSignature();
            }

            // Optional: certify
            // can be done only if version is at least 1.5 and if not already set
            // doing this on a PDF/A-1b file fails validation by Adobe preflight (PDFBOX-3821)
            // PDF/A-1b requires PDF version 1.4 max, so don't increase the version on such files.
            if (doc.getVersion() >= 1.5f && accessPermissions == 0)
            {
                SigUtils.setMDPPermission(doc, signature, 2);
            }

            PDAcroForm acroForm = doc.getDocumentCatalog().getAcroForm();
            if (acroForm != null && acroForm.getNeedAppearances())
            {
                // PDFBOX-3738 NeedAppearances true results in visible signature becoming invisible
                // with Adobe Reader
                if (acroForm.getFields().isEmpty())
                {
                    // we can safely delete it if there are no fields
                    acroForm.getCOSObject().removeItem(COSName.NEED_APPEARANCES);
                    // note that if you've set MDP permissions, the removal of this item
                    // may result in Adobe Reader claiming that the document has been changed.
                    // and/or that field content won't be displayed properly.
                    // ==> decide what you prefer and adjust your code accordingly.
                }
                else
                {
                    System.out.println("/NeedAppearances is set, signature may be ignored by Adobe Reader");
                }
            }

            // default filter
            signature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);

            // subfilter for basic and PAdES Part 2 signatures
            signature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);

            if (visibleSignatureProperties != null)
            {
                // this builds the signature structures in a separate document
                visibleSignatureProperties.buildSignature();

                signature.setName(visibleSignatureProperties.getSignerName());
                signature.setLocation(visibleSignatureProperties.getSignerLocation());
                signature.setReason(visibleSignatureProperties.getSignatureReason());
            }

            // the signing date, needed for valid signature
            signature.setSignDate(Calendar.getInstance());

            // do not set SignatureInterface instance, if external signing used
            SignatureInterface signatureInterface = isExternalSigning() ? null : this;

            // register signature dictionary and sign interface
            if (visibleSignatureProperties != null && visibleSignatureProperties.isVisualSignEnabled())
            {
                signatureOptions = new SignatureOptions();
                signatureOptions.setVisualSignature(visibleSignatureProperties.getVisibleSignature());
                signatureOptions.setPage(visibleSignatureProperties.getPage() - 1);
                doc.addSignature(signature, signatureInterface, signatureOptions);
            }
            else
            {
                doc.addSignature(signature, signatureInterface);
            }

            if (isExternalSigning())
            {
                System.out.println("Signing externally " + signedFile.getName());
                ExternalSigningSupport externalSigning = doc.saveIncrementalForExternalSigning(fos);
                // invoke external signature service
                byte[] cmsSignature = sign(externalSigning.getContent());

                // Explanation of late external signing (off by default):
                // If you want to add the signature in a separate step, then set an empty byte array
                // and call signature.getByteRange() and remember the offset signature.getByteRange()[1]+1.
                // you can write the ascii hex signature at a later time even if you don't have this
                // PDDocument object anymore, with classic java file random access methods.
                // If you can't remember the offset value from ByteRange because your context has changed,
                // then open the file with PDFBox, find the field with findExistingSignature() or
                // PODDocument.getLastSignatureDictionary() and get the ByteRange from there.
                // Close the file and then write the signature as explained earlier in this comment.
                if (isLateExternalSigning())
                {
                    // this saves the file with a 0 signature
                    externalSigning.setSignature(new byte[0]);

                    // remember the offset (add 1 because of "<")
                    int offset = signature.getByteRange()[1] + 1;

                    // now write the signature at the correct offset without any PDFBox methods
                    try (RandomAccessFile raf = new RandomAccessFile(signedFile, "rw"))
                    {
                        raf.seek(offset);
                        raf.write(Hex.getBytes(cmsSignature));
                    }
                }
                else
                {
                    // set signature bytes received from the service and save the file
                    externalSigning.setSignature(cmsSignature);
                }
            }
            else
            {
                // write incremental (only for signing purpose)
                doc.saveIncremental(fos);
            }
        }

        // Do not close signatureOptions before saving, because some COSStream objects within
        // are transferred to the signed document.
        // Do not allow signatureOptions get out of scope before saving, because then the COSDocument
        // in signature options might by closed by gc, which would close COSStream objects prematurely.
        // See https://issues.apache.org/jira/browse/PDFBOX-3743
        IOUtils.closeQuietly(signatureOptions);
    }

    private PrivateKey privateKey;
    private Certificate[] certificateChain;
    private String tsaUrl;
    private boolean externalSigning;

    public VisibleSignature(KeyStore keystore, char[] pin)
            throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException
    {
        // grabs the first alias from the keystore and get the private key. An
        // alternative method or constructor could be used for setting a specific
        // alias that should be used.
        Enumeration<String> aliases = keystore.aliases();
        String alias;
        Certificate cert = null;
        while (aliases.hasMoreElements())
        {
            alias = aliases.nextElement();
            setPrivateKey((PrivateKey) keystore.getKey(alias, pin));
            Certificate[] certChain = keystore.getCertificateChain(alias);
            if (certChain == null)
            {
                continue;
            }
            setCertificateChain(certChain);
            cert = certChain[0];
            if (cert instanceof X509Certificate)
            {
                // avoid expired certificate
                ((X509Certificate) cert).checkValidity();
            }
            break;
        }

        if (cert == null)
        {
            throw new IOException("Could not find certificate");
        }
    }

    public final void setPrivateKey(PrivateKey privateKey)
    {
        this.privateKey = privateKey;
    }

    public final void setCertificateChain(final Certificate[] certificateChain)
    {
        this.certificateChain = certificateChain;
    }

    public void setTsaUrl(String tsaUrl)
    {
        this.tsaUrl = tsaUrl;
    }

    @Override
    public byte[] sign(InputStream content) throws IOException {
        // cannot be done private (interface)
        try
        {
            List<Certificate> certList = new ArrayList<>();
            certList.addAll(Arrays.asList(certificateChain));
            Store certs = new JcaCertStore(certList);
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
            org.bouncycastle.asn1.x509.Certificate cert = org.bouncycastle.asn1.x509.Certificate.getInstance(certificateChain[0].getEncoded());
            ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA256WithRSA").build(privateKey);
            gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().build()).build(sha1Signer, new X509CertificateHolder(cert)));
            gen.addCertificates(certs);
            CMSProcessableInputStream msg = new CMSProcessableInputStream(content);
            CMSSignedData signedData = gen.generate(msg, false);
            if (tsaUrl != null && tsaUrl.length() > 0)
            {
                ValidationTimeStamp validation = new ValidationTimeStamp(tsaUrl);
                signedData = validation.addSignedTimeStamp(signedData);
            }
            return signedData.getEncoded();
        }
        catch (GeneralSecurityException | CMSException | OperatorCreationException e)
        {
            throw new IOException(e);
        }
    }

    /**
     * Set if external signing scenario should be used.
     * If {@code false}, SignatureInterface would be used for signing.
     * <p>
     *     Default: {@code false}
     * </p>
     * @param externalSigning {@code true} if external signing should be performed
     */
    public void setExternalSigning(boolean externalSigning)
    {
        this.externalSigning = externalSigning;
    }

    public boolean isExternalSigning()
    {
        return externalSigning;
    }
}
