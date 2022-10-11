"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[680],{3905:function(e,t,n){n.d(t,{Zo:function(){return p},kt:function(){return f}});var r=n(7294);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var l=r.createContext({}),c=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},p=function(e){var t=c(e.components);return r.createElement(l.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,o=e.mdxType,i=e.originalType,l=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),d=c(n),f=o,m=d["".concat(l,".").concat(f)]||d[f]||u[f]||i;return n?r.createElement(m,a(a({ref:t},p),{},{components:n})):r.createElement(m,a({ref:t},p))}));function f(e,t){var n=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var i=n.length,a=new Array(i);a[0]=d;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s.mdxType="string"==typeof e?e:o,a[1]=s;for(var c=2;c<i;c++)a[c]=n[c];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},3509:function(e,t,n){n.r(t),n.d(t,{assets:function(){return p},contentTitle:function(){return l},default:function(){return f},frontMatter:function(){return s},metadata:function(){return c},toc:function(){return u}});var r=n(7462),o=n(3366),i=(n(7294),n(3905)),a=["components"],s={},l="synapse-service-rest",c={unversionedId:"service/synapse-service-rest",id:"service/synapse-service-rest",title:"synapse-service-rest",description:"Description",source:"@site/real_docs/service/synapse-service-rest.md",sourceDirName:"service",slug:"/service/synapse-service-rest",permalink:"/synapse/docs/service/synapse-service-rest",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"tutorialSidebar",previous:{title:"synapse-framework-exception",permalink:"/synapse/docs/framework/synapse-framework-exception"},next:{title:"synapse-service-test",permalink:"/synapse/docs/service/synapse-service-test"}},p={},u=[{value:"Description",id:"description",level:2},{value:"Usage",id:"usage",level:2}],d={toc:u};function f(e){var t=e.components,n=(0,o.Z)(e,a);return(0,i.kt)("wrapper",(0,r.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"synapse-service-rest"},"synapse-service-rest"),(0,i.kt)("h2",{id:"description"},"Description"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"This synapse module is the gateway framework module used to expose Restful APIs. It provides several out-of-the-box\nfunctionalities like:"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"An open to extension generic set of abstract controller classes to easily build any concrete CRUD\nController by simply extending the needed base CRUD Controller class."),(0,i.kt)("li",{parentName:"ul"},"An open to extension configuration file that support for most of the available media types for RESTful\nwebservices. If needed, new ones could be added. Also provides a default ObjectMapper for its serialization and\ndeserialization. This can be overriden."),(0,i.kt)("li",{parentName:"ul"},"An open to extension generic ControllerExceptionHandler that handles the most common types of errors happening in\nan application."),(0,i.kt)("li",{parentName:"ul"},"An open to extension way of handling input validations."),(0,i.kt)("li",{parentName:"ul"},"An open to extension generic MetricInterceptor that will log the response time, status code and correlation\nidentifier for every request."),(0,i.kt)("li",{parentName:"ul"},"Open to extensions base request and response model to leave the code open to extension and close to modifications."),(0,i.kt)("li",{parentName:"ul"},"A generic already implemented pagination solution out of the box when calling a db."),(0,i.kt)("li",{parentName:"ul"},"A common error response object following standard naming of error fields.")))),(0,i.kt)("h2",{id:"usage"},"Usage"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"To utilize this module, add the following dependency to the pom.xml file:")),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre"},"        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-service-rest</artifactId>\n            <version>0.3.1-SNAPSHOT</version>\n        </dependency>\n")))}f.isMDXComponent=!0}}]);