package com.org.apache.camel_spring_first.ftp;


import org.apache.camel.builder.RouteBuilder;

/**
 * Server route
 */
public class MyFtpServerRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // configure properties component
        getContext().getPropertiesComponent().setLocation("classpath:ftp.properties");
        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        from("{{ftp.client}}")
            .to("file:target/download")
            .log("Downloaded file ${file:name} complete.");

        // use system out so it stand out
        System.out.println("*********************************************************************************"+"{{ftp.client}}");
        System.out.println("Camel will route files from the FTP server: "
                + getContext().resolvePropertyPlaceholders("{{ftp.client}}") + " to the target/download directory.");
        System.out.println("You can configure the location of the ftp server in the src/main/resources/ftp.properties file.");
        System.out.println("Use ctrl + c to stop this application.");
        System.out.println("*********************************************************************************");
    }
}
