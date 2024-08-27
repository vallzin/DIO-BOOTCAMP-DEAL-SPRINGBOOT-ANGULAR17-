package dio.spring.security.config;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig{

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
//                        .requestMatchers("/managers").hasRole("MANAGERS")
//                        .requestMatchers("/users").hasAnyRole("USERS", "MANAGERS")
//                        .anyRequest().authenticated()
//                ).formLogin(Customizer.withDefaults())
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.builder()
//            .username("user")
//                .password(passwordEncoder.encode("user123"))
//            .roles("USERS")
//            .build();
//
//        UserDetails admin = User.builder()
//            .username("admin")
//                .password(passwordEncoder.encode("master123"))
//            .roles("MANAGERS")
//            .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)  // Mantenha desabilitado apenas se necessário
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                        .requestMatchers("/managers").hasRole("MANAGERS")
//                        .requestMatchers("/users").hasAnyRole("USERS", "MANAGERS")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Ajuste conforme necessário
////        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
////        configuration.setAllowedHeaders(Arrays.asList("*"));
////        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user123"))
//                .roles("USERS")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("master123"))
//                .roles("MANAGERS")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


//    @Autowired
//    private SecurityDatabaseService;
//
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/login").permitAll()
//                        .requestMatchers("/managers").hasRole("MANAGERS")
//                        .requestMatchers("/users").hasAnyRole("USERS", "MANAGERS")
//                        .anyRequest().authenticated()
//                )
////                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
////        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
////        configuration.setAllowedHeaders(List.of("*"));
////        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
/*
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.builder().username("user").password(encoder.encode("user123")).roles("USERS").build(),
                User.builder().username("admin").password(encoder.encode("master123")).roles("MANAGERS").build()
        );
    }
*/

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


//}
