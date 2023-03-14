package com.hogarcontrols.hogarcloud.fleet.fleetmanager.cert;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class X509CertGenerator {

    public void generate() throws NoSuchAlgorithmException, OperatorCreationException {
        var keyPair = KeyPairGenerator.getInstance("RSA").genKeyPair();
        var subPubKeyInfo = SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded());
        var now = Instant.now();
        var validFrom = Date.from(now);
        var validTo = Date.from(now.plusSeconds(60L * 60 * 24 * 365));
        var certBuilder = new X509v3CertificateBuilder(
                new X500Name("CN=My Application,O=My Organisation,L=My City,C=DE"),
                BigInteger.ONE,
                validFrom,
                validTo,
                new X500Name("CN=My Application,O=My Organisation,L=My City,C=DE"),
                subPubKeyInfo
        );
        var signer = new JcaContentSignerBuilder("SHA256WithRSA")
                .setProvider(new BouncyCastleProvider())
                .build(keyPair.getPrivate());
        var certificate = certBuilder.build(signer);
        System.out.println(certificate);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, OperatorCreationException {
        X509CertGenerator x = new X509CertGenerator();
        x.generate();
    }
}
