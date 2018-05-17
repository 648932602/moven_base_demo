package com.modemo.javase.signature;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Store;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author moshengwei
 * @date 下午3:10年05月17日 下午3:10
 */
@RunWith(Parameterized.class)
public class SignatureMain {

    private static final String inDir = "src/main/resources/signature/";
    private static final String outDir = "target/test-output/";
    private static final String keystorePath = inDir + "keystore.p12";
    private static final String jpegPath = inDir + "stamp.jpg";
    private static final String password = "123456";
    private static Certificate certificate;

    @Parameterized.Parameter
    public boolean externallySign;

    /**
     * Values for {@link #externallySign} test parameter to specify if signing should be conducted
     * using externally signing scenario ({@code true}) or SignatureInterface ({@code false}).
     */
    @Parameterized.Parameters
    public static Collection signingTypes()
    {
        return Arrays.asList(false, true);
    }

    @BeforeClass
    public static void init() throws Exception
    {
        new File(outDir).mkdirs();

        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(new FileInputStream(keystorePath), password.toCharArray());
        certificate = keystore.getCertificateChain(keystore.aliases().nextElement())[0];
    }

    @Test
    public void testCreateVisibleSignature()
            throws IOException, CMSException, OperatorCreationException, GeneralSecurityException
    {
        // load the keystore
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(new FileInputStream(keystorePath), password.toCharArray());

        // sign PDF
//        String inPath = inDir + "sign_me.pdf";
        String inPath = "/Users/moshengwei/Downloads/iText2PDF/增租合同-陈振江-20180410_510215233581915843576.pdf";
        File destFile;
        String jpegPath = "/Users/moshengwei/Downloads/1D371B2480AC55F5076AFA93D77DCF95.png";
        try (FileInputStream fis = new FileInputStream(jpegPath))
        {
            PDDocument doc = PDDocument.load(new File(inPath));
            int pageNums = doc.getNumberOfPages();
            VisibleSignature signing = new VisibleSignature(keystore, password.toCharArray());
            signing.setVisibleSignDesigner(inPath, 60, 200, -90, fis, 1);
            signing.setVisibleSignatureProperties("name", "location", "Security", 0, pageNums, true);
            signing.setExternalSigning(externallySign);
            destFile = new File(outDir + getOutputFileName("signed{0}_visible.pdf"));
            signing.signPDF(new File(inPath), destFile, null);
        }

        checkSignature(destFile);
    }

    private String getOutputFileName(String filePattern)
    {
        return MessageFormat.format(filePattern,(externallySign ? "_ext" : ""));
    }

    // This check fails with a file created with the code before PDFBOX-3011 was solved.
    private void checkSignature(File file)
            throws IOException, CMSException, OperatorCreationException, GeneralSecurityException
    {
        try (PDDocument document = PDDocument.load(file))
        {
            List<PDSignature> signatureDictionaries = document.getSignatureDictionaries();
            if (signatureDictionaries.isEmpty())
            {
                Assert.fail("no signature found");
            }
            for (PDSignature sig : document.getSignatureDictionaries())
            {
                COSString contents = (COSString) sig.getCOSObject().getDictionaryObject(COSName.CONTENTS);
                byte[] buf;
                try (FileInputStream fis = new FileInputStream(file))
                {
                    buf = sig.getSignedContent(fis);
                }
                // inspiration:
                // http://stackoverflow.com/a/26702631/535646
                // http://stackoverflow.com/a/9261365/535646
                CMSSignedData signedData = new CMSSignedData(new CMSProcessableByteArray(buf), contents.getBytes());
                Store certificatesStore = signedData.getCertificates();
                Collection<SignerInformation> signers = signedData.getSignerInfos().getSigners();
                SignerInformation signerInformation = signers.iterator().next();
                Collection matches = certificatesStore.getMatches(signerInformation.getSID());
                X509CertificateHolder certificateHolder = (X509CertificateHolder) matches.iterator().next();
                X509Certificate certFromSignedData = new JcaX509CertificateConverter().getCertificate(certificateHolder);
                Assert.assertEquals(certificate, certFromSignedData);
                // CMSVerifierCertificateNotValidException means that the keystore wasn't valid at signing time
                if (!signerInformation.verify(new JcaSimpleSignerInfoVerifierBuilder().build(certFromSignedData)))
                {
                    Assert.fail("Signature verification failed");
                }
                break;
            }
        }
    }
}
