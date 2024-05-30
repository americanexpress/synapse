"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[641],{2451:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>c,contentTitle:()=>o,default:()=>d,frontMatter:()=>i,metadata:()=>l,toc:()=>a});var r=t(4848),s=t(5680);const i={},o="synapse-client-rest",l={id:"client/synapse-client-rest",title:"synapse-client-rest",description:"Description",source:"@site/real_docs/client/synapse-client-rest.md",sourceDirName:"client",slug:"/client/synapse-client-rest",permalink:"/synapse/docs/client/synapse-client-rest",draft:!1,unlisted:!1,tags:[],version:"current",frontMatter:{},sidebar:"tutorialSidebar",previous:{title:"Synapse",permalink:"/synapse/docs/synapse"},next:{title:"synapse-client-soap",permalink:"/synapse/docs/client/synapse-client-soap"}},c={},a=[{value:"Description",id:"description",level:2},{value:"Usage",id:"usage",level:2}];function p(e){const n={code:"code",h1:"h1",h2:"h2",li:"li",p:"p",pre:"pre",ul:"ul",...(0,s.RP)(),...e.components};return(0,r.jsxs)(r.Fragment,{children:[(0,r.jsx)(n.h1,{id:"synapse-client-rest",children:"synapse-client-rest"}),"\n",(0,r.jsx)(n.h2,{id:"description",children:"Description"}),"\n",(0,r.jsxs)(n.ul,{children:["\n",(0,r.jsxs)(n.li,{children:["\n",(0,r.jsx)(n.p,{children:"This is the synapse gateway framework utilized to consume RESTful APIs. It provides several out-of-the-box\nfunctionalities like:"}),"\n",(0,r.jsxs)(n.ul,{children:["\n",(0,r.jsx)(n.li,{children:"An open to extension BaseRestClient with several overloaded methods for every single possible restful call."}),"\n",(0,r.jsx)(n.li,{children:"A configurable ClientLoggingInterceptor that logs the request and response parameters at a configurable log level."}),"\n",(0,r.jsxs)(n.li,{children:["An open to extension BaseRestResponseErrorHandler that handles the errors generated when calling the external\nrestful APIs. This error handler has several default functionalities but is also open to extension. The\nfunctionalities are:","\n",(0,r.jsxs)(n.ul,{children:["\n",(0,r.jsx)(n.li,{children:"Log at error level the body and status code of the error returned. - Throw a HttpClientErrorException when an\nerror of the 4XX family occurs - Throw a HttpServerErrorException when an error of the 5XX family occurs."}),"\n"]}),"\n"]}),"\n",(0,r.jsx)(n.li,{children:"An open to extension generic hmac generator class. (Not sure if this applies to American Express only)."}),"\n"]}),"\n"]}),"\n"]}),"\n",(0,r.jsx)(n.h2,{id:"usage",children:"Usage"}),"\n",(0,r.jsxs)(n.ul,{children:["\n",(0,r.jsx)(n.li,{children:"To utilize this module, add the following dependency to the pom.xml file:"}),"\n"]}),"\n",(0,r.jsx)(n.pre,{children:(0,r.jsx)(n.code,{children:"        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-service-rest</artifactId>\n            <version>0.3.32-SNAPSHOT</version>\n        </dependency>\n"})})]})}function d(e={}){const{wrapper:n}={...(0,s.RP)(),...e.components};return n?(0,r.jsx)(n,{...e,children:(0,r.jsx)(p,{...e})}):p(e)}},5680:(e,n,t)=>{t.d(n,{RP:()=>a});var r=t(6540);function s(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function i(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function o(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?i(Object(t),!0).forEach((function(n){s(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):i(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function l(e,n){if(null==e)return{};var t,r,s=function(e,n){if(null==e)return{};var t,r,s={},i=Object.keys(e);for(r=0;r<i.length;r++)t=i[r],n.indexOf(t)>=0||(s[t]=e[t]);return s}(e,n);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)t=i[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(s[t]=e[t])}return s}var c=r.createContext({}),a=function(e){var n=r.useContext(c),t=n;return e&&(t="function"==typeof e?e(n):o(o({},n),e)),t},p={inlineCode:"code",wrapper:function(e){var n=e.children;return r.createElement(r.Fragment,{},n)}},d=r.forwardRef((function(e,n){var t=e.components,s=e.mdxType,i=e.originalType,c=e.parentName,d=l(e,["components","mdxType","originalType","parentName"]),u=a(t),h=s,f=u["".concat(c,".").concat(h)]||u[h]||p[h]||i;return t?r.createElement(f,o(o({ref:n},d),{},{components:t})):r.createElement(f,o({ref:n},d))}));d.displayName="MDXCreateElement"}}]);