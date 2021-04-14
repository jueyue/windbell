<template>
  <Modal v-model="isShow" width="65%" height="60%" :closable="false" :mask-closable="false" :footer-hide="true">
    <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    <div>
      <div class="search-div">
        <Row>
          <Col  span="8" style="padding-top: 5px">
            <CheckboxGroup v-model="iconsTypeSelected" @on-change="reloadData">
              <Checkbox label="iOS">
                <Icon type="logo-apple"></Icon>
                <span>苹果</span>
              </Checkbox>
              <Checkbox label="iOS-Outline">
                <Icon type="logo-reddit"></Icon>
                <span>苹果简洁</span>
              </Checkbox>
              <Checkbox label="Material Design">
                <Icon type="logo-android"></Icon>
                <span>安卓</span>
              </Checkbox>
            </CheckboxGroup>
          </Col>
          <Col span="16">
            <Input suffix="ios-search" class="search-input" v-model="searchIcon" placeholder="查找图标" style="width: auto" @on-change="reloadData" />
          </Col>
        </Row>
        </div>
      <div class="icon-div">
        <Row>
          <Col span="4" v-for="(icon ,index) in iconArr" :key="index">
            <div  class="icon-div-col"  @click="iconSelect(icon)">
              <Icon :type="icon" size="48"></Icon><br>
              <span>{{icon}}</span>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  </Modal>
</template>

<script>

export default {
  name: 'icon-select',
  data () {
    return {
      isShow: false,
      iconsTypeSelected: ['iOS'],
      iconsType: ['iOS',
        'iOS-Outline',
        'Material Design'],
      iconsNoAll: [],
      iconArr: [],
      searchIcon: '',
      call: null,
      icons: ['add',
        'add-circle',
        'alarm',
        'albums',
        'alert',
        'american-football',
        'analytics',
        'logo-android',
        'logo-angular',
        'aperture',
        'logo-apple',
        'apps',
        'appstore',
        'archive',
        'arrow-back',
        'arrow-down',
        'arrow-dropdown',
        'arrow-dropdown-circle',
        'arrow-dropleft',
        'arrow-dropleft-circle',
        'arrow-dropright',
        'arrow-dropright-circle',
        'arrow-dropup',
        'arrow-dropup-circle',
        'arrow-forward',
        'arrow-round-back',
        'arrow-round-down',
        'arrow-round-forward',
        'arrow-round-up',
        'arrow-up',
        'at',
        'attach',
        'backspace',
        'barcode',
        'baseball',
        'basket',
        'basketball',
        'battery-charging',
        'battery-dead',
        'battery-full',
        'beaker',
        'beer',
        'bicycle',
        'logo-bitcoin',
        'bluetooth',
        'boat',
        'body',
        'bonfire',
        'book',
        'bookmark',
        'bookmarks',
        'bowtie',
        'briefcase',
        'browsers',
        'brush',
        'logo-buffer',
        'bug',
        'build',
        'bulb',
        'bus',
        'cafe',
        'calculator',
        'calendar',
        'call',
        'camera',
        'car',
        'card',
        'cart',
        'cash',
        'chatboxes',
        'chatbubbles',
        'checkbox',
        'checkbox-outline',
        'checkmark',
        'checkmark-circle',
        'checkmark-circle-outline',
        'logo-chrome',
        'clipboard',
        'clock',
        'close',
        'close-circle',
        'closed-captioning',
        'cloud',
        'cloud-circle',
        'cloud-done',
        'cloud-download',
        'cloud-outline',
        'cloud-upload',
        'cloudy',
        'cloudy-night',
        'code',
        'code-download',
        'code-working',
        'logo-codepen',
        'cog',
        'color-fill',
        'color-filter',
        'color-palette',
        'color-wand',
        'compass',
        'construct',
        'contact',
        'contacts',
        'contract',
        'contrast',
        'copy',
        'create',
        'crop',
        'logo-css3',
        'cube',
        'cut',
        'desktop',
        'disc',
        'document',
        'done-all',
        'download',
        'logo-dribbble',
        'logo-dropbox',
        'easel',
        'egg',
        'logo-euro',
        'exit',
        'expand',
        'eye',
        'eye-off',
        'logo-facebook',
        'fastforward',
        'female',
        'filing',
        'film',
        'finger-print',
        'flag',
        'flame',
        'flash',
        'flask',
        'flower',
        'folder',
        'folder-open',
        'football',
        'logo-foursquare',
        'logo-freebsd-devil',
        'funnel',
        'game-controller-a',
        'game-controller-b',
        'git-branch',
        'git-commit',
        'git-compare',
        'git-merge',
        'git-network',
        'git-pull-request',
        'logo-github',
        'glasses',
        'globe',
        'logo-google',
        'logo-googleplus',
        'grid',
        'logo-hackernews',
        'hammer',
        'hand',
        'happy',
        'headset',
        'heart',
        'heart-outline',
        'help',
        'help-buoy',
        'help-circle',
        'home',
        'logo-html5',
        'ice-cream',
        'image',
        'images',
        'infinite',
        'information',
        'information-circle',
        'logo-instagram',
        'ionic',
        'ionitron',
        'logo-javascript',
        'jet',
        'key',
        'keypad',
        'laptop',
        'leaf',
        'link',
        'logo-linkedin',
        'list',
        'list-box',
        'locate',
        'lock',
        'log-in',
        'log-out',
        'magnet',
        'mail',
        'mail-open',
        'male',
        'man',
        'map',
        'logo-markdown',
        'medal',
        'medical',
        'medkit',
        'megaphone',
        'menu',
        'mic',
        'mic-off',
        'microphone',
        'moon',
        'more',
        'move',
        'musical-note',
        'musical-notes',
        'navigate',
        'no-smoking',
        'logo-nodejs',
        'notifications',
        'notifications-off',
        'notifications-outline',
        'nuclear',
        'nutrition',
        'logo-octocat',
        'open',
        'options',
        'outlet',
        'paper',
        'paper-plane',
        'partly-sunny',
        'pause',
        'paw',
        'people',
        'person',
        'person-add',
        'phone-landscape',
        'phone-portrait',
        'photos',
        'pie',
        'pin',
        'pint',
        'logo-pinterest',
        'pizza',
        'plane',
        'planet',
        'play',
        'logo-playstation',
        'podium',
        'power',
        'pricetag',
        'pricetags',
        'print',
        'pulse',
        'logo-python',
        'qr-scanner',
        'quote',
        'radio',
        'radio-button-off',
        'radio-button-on',
        'rainy',
        'recording',
        'logo-reddit',
        'redo',
        'refresh',
        'refresh-circle',
        'remove',
        'remove-circle',
        'reorder',
        'repeat',
        'resize',
        'restaurant',
        'return-left',
        'return-right',
        'reverse-camera',
        'rewind',
        'ribbon',
        'rose',
        'logo-rss',
        'sad',
        'logo-sass',
        'school',
        'search',
        'send',
        'settings',
        'share',
        'share-alt',
        'shirt',
        'shuffle',
        'skip-backward',
        'skip-forward',
        'logo-skype',
        'logo-snapchat',
        'snow',
        'speedometer',
        'square',
        'square-outline',
        'star',
        'star-half',
        'star-outline',
        'stats',
        'logo-steam',
        'stopwatch',
        'subway',
        'sunny',
        'swap',
        'switch',
        'sync',
        'tablet-landscape',
        'tablet-portrait',
        'tennisball',
        'text',
        'thermometer',
        'thumbs-down',
        'thumbs-up',
        'thunderstorm',
        'time',
        'timer',
        'train',
        'transgender',
        'trash',
        'trending-down',
        'trending-up',
        'trophy',
        'logo-tumblr',
        'logo-twitch',
        'logo-twitter',
        'umbrella',
        'undo',
        'unlock',
        'logo-usd',
        'videocam',
        'logo-vimeo',
        'volume-down',
        'volume-mute',
        'volume-off',
        'volume-up',
        'walk',
        'warning',
        'watch',
        'water',
        'logo-whatsapp',
        'wifi',
        'logo-windows',
        'wine',
        'woman',
        'logo-wordpress',
        'logo-xbox',
        'logo-yahoo',
        'logo-yen',
        'logo-youtube']
    }
  },
  methods: {
    select (call) {
      this.isShow = true
      this.call = call
    },
    closeModel () {
      this.isShow = false
    },
    iconSelect (icon) {
      this.$emit('on-select', icon)
      if (this.call) {
        this.call(icon)
      }
      this.closeModel()
    },
    reloadData () {
      this.iconArr = []
      this.iconsTypeSelected.forEach(t => {
        this.icons.forEach(icon => {
          if (!icon.startsWith('logo')) {
            if (!this.searchIcon || icon.indexOf(this.searchIcon) >= 0 ||
              ('ios-' + icon).indexOf(this.searchIcon) >= 0 ||
              ('md-' + icon).indexOf(this.searchIcon) >= 0 ||
              ('ios-' + icon + '-outline').indexOf(this.searchIcon) >= 0) {
              switch (t) {
                case 'iOS':
                  this.iconArr.push('ios-' + icon)
                  break
                case 'iOS-Outline':
                  this.iconArr.push('ios-' + icon + '-outline')
                  break
                case 'Material Design':
                  this.iconArr.push('md-' + icon)
                  break
              }
            }
          }
        })
      })
    }
  },
  mounted: function () {
    this.reloadData()
  }
}
</script>
<style scoped>
  .search-div {
    padding-top: 10px;
  }
  .icon-div {
    height: 500px;
    padding-top: 40px;
    overflow-y:auto;
  }
  .icon-div-col {
    height: 100px;
    text-align:center;
    margin:0 auto;
  }
</style>
