"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[987],{3905:function(e,n,t){t.d(n,{Zo:function(){return c},kt:function(){return d}});var r=t(7294);function a(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function i(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function o(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?i(Object(t),!0).forEach((function(n){a(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):i(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function s(e,n){if(null==e)return{};var t,r,a=function(e,n){if(null==e)return{};var t,r,a={},i=Object.keys(e);for(r=0;r<i.length;r++)t=i[r],n.indexOf(t)>=0||(a[t]=e[t]);return a}(e,n);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)t=i[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var l=r.createContext({}),p=function(e){var n=r.useContext(l),t=n;return e&&(t="function"==typeof e?e(n):o(o({},n),e)),t},c=function(e){var n=p(e.components);return r.createElement(l.Provider,{value:n},e.children)},u={inlineCode:"code",wrapper:function(e){var n=e.children;return r.createElement(r.Fragment,{},n)}},m=r.forwardRef((function(e,n){var t=e.components,a=e.mdxType,i=e.originalType,l=e.parentName,c=s(e,["components","mdxType","originalType","parentName"]),m=p(t),d=a,g=m["".concat(l,".").concat(d)]||m[d]||u[d]||i;return t?r.createElement(g,o(o({ref:n},c),{},{components:t})):r.createElement(g,o({ref:n},c))}));function d(e,n){var t=arguments,a=n&&n.mdxType;if("string"==typeof e||a){var i=t.length,o=new Array(i);o[0]=m;var s={};for(var l in n)hasOwnProperty.call(n,l)&&(s[l]=n[l]);s.originalType=e,s.mdxType="string"==typeof e?e:a,o[1]=s;for(var p=2;p<i;p++)o[p]=t[p];return r.createElement.apply(null,o)}return r.createElement.apply(null,t)}m.displayName="MDXCreateElement"},2944:function(e,n,t){t.r(n),t.d(n,{assets:function(){return c},contentTitle:function(){return l},default:function(){return d},frontMatter:function(){return s},metadata:function(){return p},toc:function(){return u}});var r=t(7462),a=t(3366),i=(t(7294),t(3905)),o=["components"],s={sidebar_position:1},l="Synapse",p={unversionedId:"synapse",id:"synapse",title:"Synapse",description:"Repository//github.com/americanexpress/synapse",source:"@site/real_docs/synapse.md",sourceDirName:".",slug:"/synapse",permalink:"/synapse/docs/synapse",draft:!1,tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"tutorialSidebar",next:{title:"synapse-client-rest",permalink:"/synapse/docs/client/synapse-client-rest"}},c={},u=[{value:"\ud83d\udcd6 Table of Contents",id:"-table-of-contents",level:2},{value:"Synapse Architecture",id:"synapse-architecture",level:2},{value:"Benefits on-top Spring",id:"benefits-on-top-spring",level:2},{value:"Credits and Acknowledgements:",id:"credits-and-acknowledgements",level:2},{value:"\ud83d\ude80\u200d Quick Start",id:"-quick-start",level:2},{value:"Building an Enterprise Ready RESTful Web Service utilizing Synapse",id:"building-an-enterprise-ready-restful-web-service-utilizing-synapse",level:3},{value:"What you will build",id:"what-you-will-build",level:3},{value:"What You Need",id:"what-you-need",level:3},{value:"Create a Resource Representation class",id:"create-a-resource-representation-class",level:3},{value:"Create a Resource Controller",id:"create-a-resource-controller",level:3},{value:"Create a API Config",id:"create-a-api-config",level:3},{value:"Create a Resource Service",id:"create-a-resource-service",level:3},{value:"Summary",id:"summary",level:3},{value:"\ud83d\udcdc Documentation",id:"-documentation",level:2},{value:"\ud83c\udfc6 Contributing",id:"-contributing",level:2},{value:"\ud83d\udddd\ufe0f License",id:"\ufe0f-license",level:2},{value:"\ud83d\udde3\ufe0f Code of Conduct",id:"\ufe0f-code-of-conduct",level:2}],m={toc:u};function d(e){var n=e.components,s=(0,a.Z)(e,o);return(0,i.kt)("wrapper",(0,r.Z)({},m,s,{components:n,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"synapse"},"Synapse"),(0,i.kt)("p",null,"Repository: ",(0,i.kt)("a",{parentName:"p",href:"https://github.com/americanexpress/synapse"},"https://github.com/americanexpress/synapse")),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"Tired of spending time and struggling in designing your foundational architecture and starting out your project?"),(0,i.kt)("li",{parentName:"ul"},"Tired of having the developers in your team not following the same standards and each of them doing things their own\nway?"),(0,i.kt)("li",{parentName:"ul"},"Tired of having a disorganized code base difficult to maintain after just a few months of starting your project?"),(0,i.kt)("li",{parentName:"ul"},"Tired of spending countless hours, days, or months in getting your foundational frameworks robust and reliable?")),(0,i.kt)("p",null,"If any of these sound like situations you have experienced or situations you simply want to avoid, then Synapse is what\nyou need."),(0,i.kt)("p",null,"Synapse is a set of lightweight modules designed to speed up development time and help developers build out their\napplications. The modules are focused towards server side implementation. The modules serve as a wide range tool-suite\nfor developers to facilitate rapid development, with high quality built-in. It is designed with the purpose of helping\ndevelopers create web services in a quick and easy way following strict conventions. Synapse provides an abstraction\nlayer that enforces developers to follow SOLID principles and avoid common mistakes during the development process. It\nis based on keeping things ",(0,i.kt)("em",{parentName:"p"},"simple")," and ",(0,i.kt)("em",{parentName:"p"},"clear"),". The framework strongly encourages convention over configuration, while\nhighlighting the criticality of structure. Although Synapse will cover a majority of the scenarios you will need when\ncreating an enterprise-grade application, it is also very open to extension. We encourage you to extend and implement\nthe abstraction layer of this framework. Furthermore, if you feel the need to create a new feature not covered here, you\nalso have that flexibility."),(0,i.kt)("h2",{id:"-table-of-contents"},"\ud83d\udcd6 Table of Contents"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"#-quick-start"},"Quick Start")),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"#-documentation"},"Documentation")),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"#%EF%B8%8F-license"},"License")),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"#%EF%B8%8F-code-of-conduct"},"Code Of Conduct")),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"#community"},"Community")),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"#-contributing"},"Contributing"))),(0,i.kt)("h2",{id:"synapse-architecture"},"Synapse Architecture"),(0,i.kt)("p",null,(0,i.kt)("img",{alt:"Synapse Architecture",src:t(5135).Z,width:"1920",height:"1080"})),(0,i.kt)("h2",{id:"benefits-on-top-spring"},"Benefits on-top Spring"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"The Synapse framework is an extension of Spring that uses best practices to force compliance to good standards, while\nalso simplifying the development process. These are several of the benefits it provides on-top of what Spring already\nprovides:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Creates a very structured and organized architecture very easy to follow by anyone, junior and senior developers."),(0,i.kt)("li",{parentName:"ul"},"Enforces developers to follow the same template and good standards across the entire code base."),(0,i.kt)("li",{parentName:"ul"},"Forces strict separation of concerns because the base 'Hook' classes which the developers extend from are already\nrepresenting each layer in the famous and already proven three layer architecture. - Base\nController (Http Layer). - BaseService (Service Layer). - BaseRestClient, BaseSoapClient or\nRepositories(These are already interfaces so not base classes provided) (DAO Layer)."),(0,i.kt)("li",{parentName:"ul"},"Provides the BaseControllerTest class with a set of overloaded methods to facilitate controllers slice testing."),(0,i.kt)("li",{parentName:"ul"},"Provides a couchbase library to build dynamic queries based on Spring Data and Query DSL (There is nothing like\nthis out there)."),(0,i.kt)("li",{parentName:"ul"},"Provides a ControllersExceptionHandlers that handles most of the common exceptions."),(0,i.kt)("li",{parentName:"ul"},"Provides an elegant and very simple to use exception handling mechanism with only two custom runtime exceptions\nthat covers all the possible scenarios."),(0,i.kt)("li",{parentName:"ul"},"Provides a generic way to connect to any relational database, using h2 for local and unit tests. And leave free\nthe use of any desired relational database for other environments.")))),(0,i.kt)("h2",{id:"credits-and-acknowledgements"},"Credits and Acknowledgements:"),(0,i.kt)("p",null,(0,i.kt)("strong",{parentName:"p"},"Authors")),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"Gabriel Jimenez"),(0,i.kt)("li",{parentName:"ul"},"Alexei Morgado"),(0,i.kt)("li",{parentName:"ul"},"Paolo Claudio"),(0,i.kt)("li",{parentName:"ul"},"Darien Liburd"),(0,i.kt)("li",{parentName:"ul"},"Shahzada Azam"),(0,i.kt)("li",{parentName:"ul"},"Krishna Kuchikulla")),(0,i.kt)("h2",{id:"-quick-start"},"\ud83d\ude80\u200d Quick Start"),(0,i.kt)("h3",{id:"building-an-enterprise-ready-restful-web-service-utilizing-synapse"},"Building an Enterprise Ready RESTful Web Service utilizing Synapse"),(0,i.kt)("h3",{id:"what-you-will-build"},"What you will build"),(0,i.kt)("p",null,"This guide walks you through the process of creating a \u201cHello, World\u201d RESTful web service with Synapse."),(0,i.kt)("p",null,"It will respond with a JSON representation of a greeting, as the following listing shows:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-json"},'   {\n  "message": "Hello, World!"\n}\n')),(0,i.kt)("p",null,"You can customize the greeting with an optional name parameter in the query string, as the following listing shows:"),(0,i.kt)("p",null,(0,i.kt)("inlineCode",{parentName:"p"},"http://localhost:8080/greeting?name=User")),(0,i.kt)("p",null,"The name parameter value overrides the default value of World and is reflected in the response, as the following listing\nshows:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-json"},'   {\n  "message": "Hello, User!"\n}\n')),(0,i.kt)("h3",{id:"what-you-need"},"What You Need"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"About 15 minutes"),(0,i.kt)("li",{parentName:"ul"},"A favorite text editor or IDE"),(0,i.kt)("li",{parentName:"ul"},"JDK 1.8+"),(0,i.kt)("li",{parentName:"ul"},"Gradle 4+ or Maven 3.2+")),(0,i.kt)("p",null,"You can also import the code straight into your IDE:"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"IntelliJ IDEA"),(0,i.kt)("li",{parentName:"ul"},"Spring Tool Suite (STS)")),(0,i.kt)("p",null,"The following listing shows the pom.xml file that is created when you choose Maven:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-xml"},'<?xml version="1.0" encoding="UTF-8"?>\n<project xmlns="http://maven.apache.org/POM/4.0.0"\n         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\n         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">\n    <parent>\n        <groupId>com.example</groupId>\n        <artifactId>service</artifactId>\n        <version>0.3.14-SNAPSHOT</version>\n    </parent>\n\n    <modelVersion>4.0.0</modelVersion>\n    <groupId>com.example.synapse</groupId>\n    <artifactId>service-greeting</artifactId>\n    <version>0.3.14-SNAPSHOT</version>\n\n    <properties>\n        <start-class>com.example.synapse.bookstore.GreetingApplication</start-class>\n    </properties>\n\n    <dependencies>\n        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-service-rest</artifactId>\n            <version>0.3.14-SNAPSHOT</version>\n        </dependency>\n    </dependencies>\n\n    <build>\n        <plugins>\n            <plugin>\n                <groupId>org.springframework.boot</groupId>\n                <artifactId>spring-boot-maven-plugin</artifactId>\n                <executions>\n                    <execution>\n                        <goals>\n                            <goal>repackage</goal>\n                        </goals>\n                    </execution>\n                </executions>\n            </plugin>\n            <plugin>\n                <artifactId>maven-compiler-plugin</artifactId>\n            </plugin>\n            <plugin>\n                <artifactId>maven-jar-plugin</artifactId>\n            </plugin>\n        </plugins>\n    </build>\n\n</project>\n\n')),(0,i.kt)("h3",{id:"create-a-resource-representation-class"},"Create a Resource Representation class"),(0,i.kt)("p",null,"Now that you have set up the project and build system, you can create your web service."),(0,i.kt)("p",null,"Begin the process by thinking about service interactions."),(0,i.kt)("p",null,"The service will handle POST requests for /greetings, optionally with a name parameter in the query string. The POST\nrequest should return a 200 OK response with JSON in the body that represents a greeting. It should resemble the\nfollowing output:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-json"},'   {\n  "message": "Hello, User!"\n}\n')),(0,i.kt)("p",null,"The id field is a unique identifier for the greeting, and content is the textual representation of the greeting."),(0,i.kt)("p",null,"To model the greeting representation, create a resource representation class. To do so, provide a plain old Java object\nwith fields, constructors, and accessors for the id and content data, as the following listing (from\nsrc/main/java/com/example/restservice/Greeting.java) shows:"),(0,i.kt)("p",null,"Below is the request model."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"package com.example.synapse.bookstore.model;\n\nimport BaseServiceRequest;\n\nimport java.util.Objects;\n\npublic class GreetingRequest extends BaseServiceRequest {\n\n    private String name;\n\n    public GreetingRequest(String name) {\n        this.name = name;\n    }\n\n    public String getName() {\n        return name;\n    }\n\n    public void setName(String name) {\n        this.name = name;\n    }\n\n    @Override\n    public boolean equals(Object o) {\n        if (this == o) return true;\n        if (o == null || getClass() != o.getClass()) return false;\n\n        GreetingRequest that = (GreetingRequest) o;\n\n        return Objects.equals(name, that.name);\n    }\n\n    @Override\n    public int hashCode() {\n        return name != null ? name.hashCode() : 0;\n    }\n}\n\n")),(0,i.kt)("p",null,"Below is the response model."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"package com.example.synapse.bookstore.model;\n\nimport BaseServiceResponse;\n\nimport java.util.Objects;\n\npublic class GreetingResponse extends BaseServiceResponse {\n\n    private String message;\n\n    public GreetingResponse(String message) {\n        this.message = message;\n    }\n\n    public String getMessage() {\n        return message;\n    }\n\n    public void setMessage(String message) {\n        this.message = message;\n    }\n\n\n    @Override\n    public String toString() {\n        return \"GreetingResponse{\" +\n                \"message='\" + message + '\\'' +\n                '}';\n    }\n\n    @Override\n    public boolean equals(Object o) {\n        if (this == o) return true;\n        if (o == null || getClass() != o.getClass()) return false;\n\n        GreetingResponse that = (GreetingResponse) o;\n\n        return Objects.equals(message, that.message);\n    }\n\n    @Override\n    public int hashCode() {\n        return message != null ? message.hashCode() : 0;\n    }\n}\n")),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"package com.example.synapse.bookstore;\n\nimport org.springframework.boot.SpringApplication;\nimport org.springframework.boot.autoconfigure.SpringBootApplication;\n\n/**\n * <code>GreetingApplication</code> class that runs this service.\n *\n * @author Gabriel Jimenez\n */\n@SpringBootApplication\npublic class GreetingApplication {\n\n    /**\n     * Run the application as a SpringBoot application.\n     *\n     * @param args program arguments\n     */\n    public static void main(String[] args) {\n        SpringApplication.run(GreetingApplication.class, args);\n    }\n\n}\n\n")),(0,i.kt)("h3",{id:"create-a-resource-controller"},"Create a Resource Controller"),(0,i.kt)("p",null,"In Spring\u2019s approach to building RESTful web services, HTTP requests are handled by a controller. These components are\nidentified by the @RestController annotation, and the GreetingController shown in the following listing (from\nsrc/main/java/com/example/restservice/GreetingController.java)\nhandles GET requests for /greeting by returning a new instance of the Greeting class:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},'package com.example.synapse.bookstore.controller;\n\nimport BaseController;\nimport com.example.synapse.bookstore.model.GreetingRequest;\nimport com.example.synapse.bookstore.model.GreetingResponse;\nimport com.example.synapse.bookstore.service.GreetingService;\nimport io.swagger.annotations.Api;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n\n/**\n * <code>GreetingController</code> class is the controller that handles the greeting requests.\n * on the endpoint /greetings\n *\n * @author Gabriel Jimenez\n */\n@RestController\n@RequestMapping("/greetings")\n@Api(value = "Greeting API", tags = "Greeting")\npublic class GreetingController extends BaseController<GreetingRequest, GreetingResponse, GreetingService> {\n}\n')),(0,i.kt)("h3",{id:"create-a-api-config"},"Create a API Config"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},'package com.example.synapse.bookstore.config;\n\nimport com.americanexpress.synapse.service.rest.config.BaseServiceRestConfig;\nimport org.springframework.context.annotation.ComponentScan;\nimport org.springframework.context.annotation.Configuration;\nimport org.springframework.context.annotation.Import;\nimport org.springframework.context.annotation.PropertySource;\nimport org.springframework.web.servlet.config.annotation.WebMvcConfigurer;\n\n/**\n * <code>GreetingConfig</code> class sets configurations used in this module.\n *\n * @author Gabriel Jimenez\n */\n@Configuration\n@PropertySource("classpath:/service-greeting-application.properties")\n@Import({BaseServiceRestConfig.class})\n@ComponentScan(basePackages = "com.example.synapse.bookstore")\npublic class GreetingConfig implements WebMvcConfigurer {\n}\n')),(0,i.kt)("h3",{id:"create-a-resource-service"},"Create a Resource Service"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},'package com.example.synapse.bookstore.service;\n\nimport ServiceHeaders;\nimport BaseService;\nimport com.example.synapse.bookstore.model.GreetingRequest;\nimport com.example.synapse.bookstore.model.GreetingResponse;\nimport org.springframework.stereotype.Service;\n\nimport static java.util.Objects.nonNull;\n\n/**\n * The <code>GreetingService</code> class is responsible handling the business logic of the Greeting API. \n */\n@Service\npublic class GreetingService extends BaseService<GreetingRequest, GreetingResponse> {\n\n    private static final String template = "Hello, %s!";\n\n    @Override\n    protected GreetingResponse getResource(ServiceHeaders headers, GreetingRequest request) {\n        if (nonNull(request.getName())) {\n            return new GreetingResponse(String.format(template, request.getName()));\n        } else {\n            return new GreetingResponse(String.format(template, "World!"));\n        }\n    }\n}\n')),(0,i.kt)("p",null,"Now make sure to set your environment to ",(0,i.kt)("inlineCode",{parentName:"p"},"local"),":"),(0,i.kt)("h3",{id:"summary"},"Summary"),(0,i.kt)("p",null,"Congratulations! You built a simple web application utilizing Synapse and learned how it can ramp up your development\npace. You also turned on some handy production services. This is only a small sampling of what Synapse can do."),(0,i.kt)("h2",{id:"-documentation"},"\ud83d\udcdc Documentation"),(0,i.kt)("p",null,(0,i.kt)("a",{parentName:"p",href:"https://americanexpress.io/synapse/"},"https://americanexpress.io/synapse/")),(0,i.kt)("h2",{id:"-contributing"},"\ud83c\udfc6 Contributing"),(0,i.kt)("p",null,"We welcome Your interest in the American Express Open Source Community on Github. Any Contributor to any Open Source\nProject managed by the American Express Open Source Community must accept and sign an Agreement indicating agreement to\nthe terms below. Except for the rights granted in this Agreement to American Express and to recipients of software\ndistributed by American Express, You reserve all right, title, and interest, if any, in and to Your Contributions.\nPlease\n",(0,i.kt)("a",{parentName:"p",href:"https://cla-assistant.io/americanexpress/synapse"},"fill out the Agreement"),"."),(0,i.kt)("h2",{id:"\ufe0f-license"},"\ud83d\udddd\ufe0f License"),(0,i.kt)("p",null,"Any contributions made under this project will be governed by the\n",(0,i.kt)("a",{parentName:"p",href:"./LICENSE"},"Apache License 2.0"),"."),(0,i.kt)("h2",{id:"\ufe0f-code-of-conduct"},"\ud83d\udde3\ufe0f Code of Conduct"),(0,i.kt)("p",null,"This project adheres to the ",(0,i.kt)("a",{parentName:"p",href:"./CODE_OF_CONDUCT.md"},"American Express Community Guidelines"),". By participating, you are\nexpected to honor these guidelines."))}d.isMDXComponent=!0},5135:function(e,n,t){n.Z=t.p+"assets/images/synapse-architecture-1-155b44c66cccb86c8a03f5319531ee4d.png"}}]);