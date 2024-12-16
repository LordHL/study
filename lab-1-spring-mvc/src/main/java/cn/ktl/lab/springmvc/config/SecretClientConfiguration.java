//package cn.ktl.lab.springmvc.config;
//
//import com.azure.identity.DefaultAzureCredentialBuilder;
//import com.azure.security.keyvault.secrets.SecretClient;
//import com.azure.security.keyvault.secrets.SecretClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author lin ho
// * Des: TODO
// */
//@Configuration
//public class SecretClientConfiguration {
//
//    @Value("${azure.keyvault.uri:https://oneformadevoppsappkv-dev.vault.azure.net/}")
//    private String azKvUri;
//    @Bean
//    public SecretClient createSecretClient() {
//        return new SecretClientBuilder()
//                .vaultUrl(azKvUri)
//                .credential(new DefaultAzureCredentialBuilder().build())
//                .buildClient();
//    }
//
//}
