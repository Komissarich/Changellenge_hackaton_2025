import { BuildOptions as EsbuildConfiguration } from "esbuild";

interface QuasarBexConfiguration {
  /**
   * The list of extra scripts (js/ts) not in your bex manifest that you want to
   * compile and use in your browser extension. Maybe dynamic use them?
   *
   * Each entry in the list should be a relative filename to /src-bex/
   *
   * @example [ 'my-script.ts', 'sub-folder/my-other-script.js' ]
   */
  extraScripts?: string[];

  /**
   * Extend the Esbuild config that is used for the bex scripts
   * (background, content scripts, dom script)
   */
  extendBexScriptsConf?: (config: EsbuildConfiguration) => void;

  /**
   * Should you need some dynamic changes to the Browser Extension manifest file
   * (/src-bex/manifest.json) then use this method to do it.
   */
  extendBexManifestJson?: (json: object) => void;
}
