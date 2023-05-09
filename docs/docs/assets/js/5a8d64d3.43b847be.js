"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[139],{3905:function(e,r,t){t.d(r,{Zo:function(){return l},kt:function(){return m}});var n=t(7294);function o(e,r,t){return r in e?Object.defineProperty(e,r,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[r]=t,e}function a(e,r){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);r&&(n=n.filter((function(r){return Object.getOwnPropertyDescriptor(e,r).enumerable}))),t.push.apply(t,n)}return t}function i(e){for(var r=1;r<arguments.length;r++){var t=null!=arguments[r]?arguments[r]:{};r%2?a(Object(t),!0).forEach((function(r){o(e,r,t[r])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):a(Object(t)).forEach((function(r){Object.defineProperty(e,r,Object.getOwnPropertyDescriptor(t,r))}))}return e}function s(e,r){if(null==e)return{};var t,n,o=function(e,r){if(null==e)return{};var t,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)t=a[n],r.indexOf(t)>=0||(o[t]=e[t]);return o}(e,r);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)t=a[n],r.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(o[t]=e[t])}return o}var p=n.createContext({}),c=function(e){var r=n.useContext(p),t=r;return e&&(t="function"==typeof e?e(r):i(i({},r),e)),t},l=function(e){var r=c(e.components);return n.createElement(p.Provider,{value:r},e.children)},u={inlineCode:"code",wrapper:function(e){var r=e.children;return n.createElement(n.Fragment,{},r)}},f=n.forwardRef((function(e,r){var t=e.components,o=e.mdxType,a=e.originalType,p=e.parentName,l=s(e,["components","mdxType","originalType","parentName"]),f=c(t),m=o,d=f["".concat(p,".").concat(m)]||f[m]||u[m]||a;return t?n.createElement(d,i(i({ref:r},l),{},{components:t})):n.createElement(d,i({ref:r},l))}));function m(e,r){var t=arguments,o=r&&r.mdxType;if("string"==typeof e||o){var a=t.length,i=new Array(a);i[0]=f;var s={};for(var p in r)hasOwnProperty.call(r,p)&&(s[p]=r[p]);s.originalType=e,s.mdxType="string"==typeof e?e:o,i[1]=s;for(var c=2;c<a;c++)i[c]=t[c];return n.createElement.apply(null,i)}return n.createElement.apply(null,t)}f.displayName="MDXCreateElement"},5242:function(e,r,t){t.r(r),t.d(r,{assets:function(){return l},contentTitle:function(){return p},default:function(){return m},frontMatter:function(){return s},metadata:function(){return c},toc:function(){return u}});var n=t(7462),o=t(3366),a=(t(7294),t(3905)),i=["components"],s={},p="synapse-framework-exception",c={unversionedId:"framework/synapse-framework-exception",id:"framework/synapse-framework-exception",title:"synapse-framework-exception",description:"Description",source:"@site/real_docs/framework/synapse-framework-exception.md",sourceDirName:"framework",slug:"/framework/synapse-framework-exception",permalink:"/synapse/docs/framework/synapse-framework-exception",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"tutorialSidebar",previous:{title:"synapse-framework-api-docs",permalink:"/synapse/docs/framework/synapse-framework-api-docs"},next:{title:"synapse-service-rest",permalink:"/synapse/docs/service/synapse-service-rest"}},l={},u=[{value:"Description",id:"description",level:2},{value:"Usage",id:"usage",level:2}],f={toc:u};function m(e){var r=e.components,t=(0,o.Z)(e,i);return(0,a.kt)("wrapper",(0,n.Z)({},f,t,{components:r,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"synapse-framework-exception"},"synapse-framework-exception"),(0,a.kt)("h2",{id:"description"},"Description"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("p",{parentName:"li"},"This is the synapse module that provides the two Exception classes you will ever need in your application and also an\nelegant mechanism to handle them. It provides several out-of-the-box functionalities like:"),(0,a.kt)("ul",{parentName:"li"},(0,a.kt)("li",{parentName:"ul"},"An ApplicationServerException to wrap the caught checked exceptions thrown by the application."),(0,a.kt)("li",{parentName:"ul"},"An ApplicationClientException to return a user-friendly message to the users when a warning or message needs to be\nreturned."),(0,a.kt)("li",{parentName:"ul"},"A generic open to extension ErrorCode enum with the most common error codes used by any application."),(0,a.kt)("li",{parentName:"ul"},"A generic open to extension error-messages.properties file that uses the error codes from the ErrorCode enum\nmentioned above.")))),(0,a.kt)("h2",{id:"usage"},"Usage"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"To utilize this module, add the following dependency to the pom.xml file:")),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-framework-exception</artifactId>\n            <version>0.3.16-SNAPSHOT</version>\n        </dependency>\n")))}m.isMDXComponent=!0}}]);