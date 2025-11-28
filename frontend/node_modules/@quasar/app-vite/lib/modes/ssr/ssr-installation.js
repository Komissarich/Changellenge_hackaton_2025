import fse from 'fs-extra'

import { log, warn } from '../../utils/logger.js'
import { isModeInstalled } from '../modes-utils.js'

export async function addMode ({
  ctx: { appPaths, cacheProxy },
  silent
}) {
  if (isModeInstalled(appPaths, 'ssr')) {
    if (silent !== true) {
      warn('SSR support detected already. Aborting.')
    }
    return
  }

  log('Creating SSR source folder...')
  const hasTypescript = await cacheProxy.getModule('hasTypescript')
  const format = hasTypescript ? 'ts' : 'js'
  fse.copySync(
    appPaths.resolve.cli(`templates/ssr/${ format }`),
    appPaths.ssrDir
  )

  log('SSR support was added')
}

export function removeMode ({
  ctx: { appPaths }
}) {
  if (isModeInstalled(appPaths, 'ssr') === false) {
    warn('No SSR support detected. Aborting.')
    return
  }

  log('Removing SSR source folder')
  fse.removeSync(appPaths.ssrDir)
  log('SSR support was removed')
}
