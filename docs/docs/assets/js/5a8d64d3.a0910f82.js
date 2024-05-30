"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[568],{7232:(e,n,r)=>{r.r(n),r.d(n,{assets:()=>c,contentTitle:()=>s,default:()=>d,frontMatter:()=>i,metadata:()=>a,toc:()=>p});var t=r(4848),o=r(5680);const i={},s="synapse-framework-exception",a={id:"framework/synapse-framework-exception",title:"synapse-framework-exception",description:"Description",source:"@site/real_docs/framework/synapse-framework-exception.md",sourceDirName:"framework",slug:"/framework/synapse-framework-exception",permalink:"/synapse/docs/framework/synapse-framework-exception",draft:!1,unlisted:!1,tags:[],version:"current",frontMatter:{},sidebar:"tutorialSidebar",previous:{title:"synapse-framework-api-docs",permalink:"/synapse/docs/framework/synapse-framework-api-docs"},next:{title:"synapse-service-rest",permalink:"/synapse/docs/service/synapse-service-rest"}},c={},p=[{value:"Description",id:"description",level:2},{value:"Usage",id:"usage",level:2}];function l(e){const n={code:"code",h1:"h1",h2:"h2",li:"li",p:"p",pre:"pre",ul:"ul",...(0,o.RP)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(n.h1,{id:"synapse-framework-exception",children:"synapse-framework-exception"}),"\n",(0,t.jsx)(n.h2,{id:"description",children:"Description"}),"\n",(0,t.jsxs)(n.ul,{children:["\n",(0,t.jsxs)(n.li,{children:["\n",(0,t.jsx)(n.p,{children:"This is the synapse module that provides the two Exception classes you will ever need in your application and also an\nelegant mechanism to handle them. It provides several out-of-the-box functionalities like:"}),"\n",(0,t.jsxs)(n.ul,{children:["\n",(0,t.jsx)(n.li,{children:"An ApplicationServerException to wrap the caught checked exceptions thrown by the application."}),"\n",(0,t.jsx)(n.li,{children:"An ApplicationClientException to return a user-friendly message to the users when a warning or message needs to be\nreturned."}),"\n",(0,t.jsx)(n.li,{children:"A generic open to extension ErrorCode enum with the most common error codes used by any application."}),"\n",(0,t.jsx)(n.li,{children:"A generic open to extension error-messages.properties file that uses the error codes from the ErrorCode enum\nmentioned above."}),"\n"]}),"\n"]}),"\n"]}),"\n",(0,t.jsx)(n.h2,{id:"usage",children:"Usage"}),"\n",(0,t.jsxs)(n.ul,{children:["\n",(0,t.jsx)(n.li,{children:"To utilize this module, add the following dependency to the pom.xml file:"}),"\n"]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{children:"        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-framework-exception</artifactId>\n            <version>0.3.32-SNAPSHOT</version>\n        </dependency>\n"})})]})}function d(e={}){const{wrapper:n}={...(0,o.RP)(),...e.components};return n?(0,t.jsx)(n,{...e,children:(0,t.jsx)(l,{...e})}):l(e)}},5680:(e,n,r)=>{r.d(n,{RP:()=>p});var t=r(6540);function o(e,n,r){return n in e?Object.defineProperty(e,n,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[n]=r,e}function i(e,n){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var t=Object.getOwnPropertySymbols(e);n&&(t=t.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),r.push.apply(r,t)}return r}function s(e){for(var n=1;n<arguments.length;n++){var r=null!=arguments[n]?arguments[n]:{};n%2?i(Object(r),!0).forEach((function(n){o(e,n,r[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):i(Object(r)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(r,n))}))}return e}function a(e,n){if(null==e)return{};var r,t,o=function(e,n){if(null==e)return{};var r,t,o={},i=Object.keys(e);for(t=0;t<i.length;t++)r=i[t],n.indexOf(r)>=0||(o[r]=e[r]);return o}(e,n);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(t=0;t<i.length;t++)r=i[t],n.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(o[r]=e[r])}return o}var c=t.createContext({}),p=function(e){var n=t.useContext(c),r=n;return e&&(r="function"==typeof e?e(n):s(s({},n),e)),r},l={inlineCode:"code",wrapper:function(e){var n=e.children;return t.createElement(t.Fragment,{},n)}},d=t.forwardRef((function(e,n){var r=e.components,o=e.mdxType,i=e.originalType,c=e.parentName,d=a(e,["components","mdxType","originalType","parentName"]),u=p(r),f=o,m=u["".concat(c,".").concat(f)]||u[f]||l[f]||i;return r?t.createElement(m,s(s({ref:n},d),{},{components:r})):t.createElement(m,s({ref:n},d))}));d.displayName="MDXCreateElement"}}]);