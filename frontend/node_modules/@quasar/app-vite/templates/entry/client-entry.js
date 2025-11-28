/* eslint-disable */
/**
 * THIS FILE IS GENERATED AUTOMATICALLY.
 * DO NOT EDIT.
 *
 * You are probably looking on adding startup/initialization code.
 * Use "quasar new boot <name>" and add it there.
 * One boot file per concern. Then reference the file(s) in quasar.config file > boot:
 * boot: ['file', ...] // do not add ".js" extension to it.
 *
 * Boot files are your "main.js"
 **/

<% if (ctx.mode.ssr && ctx.mode.pwa) { %>
import { createSSRApp, createApp } from 'vue'
<% } else { %>
import { <%= ctx.mode.ssr ? 'createSSRApp' : 'createApp' %> } from 'vue'
<% } %>

<% if (ctx.mode.bex) { %>
import './bex-app.js'
<% } %>

<% const bootEntries = boot.filter(asset => asset.client !== false) %>

<% extras.length !== 0 && extras.filter(asset => asset).forEach(asset => { %>
import '@quasar/extras/<%= asset %>/<%= asset %>.css'
<% }) %>

<% animations.length !== 0 && animations.filter(asset => asset).forEach(asset => { %>
import '@quasar/extras/animate/<%= asset %>.css'
<% }) %>

// We load Quasar stylesheet file
import 'quasar/dist/quasar.<%= metaConf.css.quasarSrcExt %>'

<% if (framework.cssAddon) { %>
// We add Quasar addons, if they were requested
import 'quasar/src/css/flex-addon.sass'
<% } %>

<% css.length !== 0 && css.filter(asset => asset.client !== false).forEach(asset => { %>
import '<%= asset.path %>'
<% }) %>

import createQuasarApp<% if (ctx.mode.ssr && ctx.mode.pwa) { %>, { ssrIsRunningOnClientPWA }<% } %> from './app.js'
import quasarUserOptions from './quasar-user-options.js'

<% if (ctx.mode.pwa) { %>
import 'app/<%= sourceFiles.pwaRegisterServiceWorker %>'
<% } %>

<% if (preFetch) { %>
import { addPreFetchHooks } from './client-prefetch.js'
<% } %>

<% if (ctx.dev) { %>
console.info('[Quasar] Running <%= ctx.modeName.toUpperCase() + (ctx.mode.ssr && ctx.mode.pwa ? ' + PWA' : '') %>.')
<% } %>

const publicPath = `<%= build.publicPath %>`

async function start ({
  app,
  router
  <%= metaConf.hasStore ? ', store' : '' %>
}<%= bootEntries.length !== 0 ? ', bootFiles' : '' %>) {
  <% if (bootEntries.length !== 0) { %>
  let hasRedirected = false
  const getRedirectUrl = url => {
    try { return router.resolve(url).href }
    catch (err) {}

    return Object(url) === url
      ? null
      : url
  }
  const redirect = url => {
    hasRedirected = true

    if (typeof url === 'string' && /^https?:\/\//.test(url)) {
      window.location.href = url
      return
    }

    const href = getRedirectUrl(url)

    // continue if we didn't fail to resolve the url
    if (href !== null) {
      window.location.href = href
      <%= build.vueRouterMode === 'hash' ? 'window.location.reload()' : '' %>
    }
  }

  const urlPath = window.location.href.replace(window.location.origin, '')

  for (let i = 0; hasRedirected === false && i < bootFiles.length; i++) {
    try {
      await bootFiles[i]({
        app,
        router,
        <%= metaConf.hasStore ? 'store,' : '' %>
        ssrContext: null,
        redirect,
        urlPath,
        publicPath
      })
    }
    catch (err) {
      if (err && err.url) {
        redirect(err.url)
        return
      }

      console.error('[Quasar] boot error:', err)
      return
    }
  }

  if (hasRedirected === true) return
  <% } %>

  app.use(router)

  <% if (ctx.mode.ssr) { %>
    <% if (ctx.mode.pwa) { %>
      if (ssrIsRunningOnClientPWA === true) {
        <% if (preFetch) { %>
        addPreFetchHooks({ router, ssrIsRunningOnClientPWA<%= metaConf.hasStore ? ', store' : '' %> })
        <% } %>
        app.mount('#q-app')
      }
      else {
    <% } %>
    // wait until router has resolved all async before hooks
    // and async components...
    router.isReady().then(() => {
      <% if (preFetch) { %>
      addPreFetchHooks({ router<%= metaConf.hasStore ? ', store' : '' %>, publicPath })
      <% } %>
      app.mount('#q-app')
    })
    <% if (ctx.mode.pwa) { %>
    }
    <% } %>

  <% } else { // not SSR %>

    <% if (preFetch) { %>
    addPreFetchHooks({ router<%= metaConf.hasStore ? ', store' : '' %> })
    <% } %>

    <% if (ctx.mode.cordova) { %>
      document.addEventListener('deviceready', () => {
        app.config.globalProperties.$q.cordova = window.cordova
        app.mount('#q-app')
      }, false) // on deviceready
    <% } else { %>
      app.mount('#q-app')
    <% } %>
  <% } // end of Non SSR %>

}

createQuasarApp(<%=
  ctx.mode.ssr
    ? (ctx.mode.pwa ? 'ssrIsRunningOnClientPWA ? createApp : createSSRApp' : 'createSSRApp')
    : 'createApp'
%>, quasarUserOptions)
<% if (bootEntries.length !== 0) { %>
  .then(app => {
    // eventually remove this when Cordova/Capacitor/Electron support becomes old
    const [ method, mapFn ] = Promise.allSettled !== void 0
      ? [
        'allSettled',
        bootFiles => bootFiles.map(result => {
          if (result.status === 'rejected') {
            console.error('[Quasar] boot error:', result.reason)
            return
          }
          return result.value.default
        })
      ]
      : [
        'all',
        bootFiles => bootFiles.map(entry => entry.default)
      ]

    return Promise[ method ]([
      <% bootEntries.forEach((asset, index) => { %>
      import('<%= asset.path %>')<%= index < bootEntries.length - 1 ? ',' : '' %>
      <% }) %>
    ]).then(bootFiles => {
      const boot = mapFn(bootFiles).filter(entry => typeof entry === 'function')
      start(app, boot)
    })
  })
<% } else { %>
  .then(start)
<% } %>
