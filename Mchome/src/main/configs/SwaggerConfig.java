@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("MBTIgram Springdoc")
                .description("Springdoc을 사용한 MBTIgram Swagger UI")
                .version("1.0.0");
    }
}
