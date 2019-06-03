const btns = {
  update: (h, params, vm) => {
    return h('Button', {
      props: {
        type: 'primary',
        icon: 'ios-create-outline'
      },
      style: {
        marginRight: '5px'
      },
      on: {
        click: () => {
          vm.$emit('on-update', params)
        }
      }
    }, '编辑')
  },
  detail: (h, params, vm) => {
    return h('Button', {
      props: {
        type: 'primary',
        icon: 'md-document'
      },
      style: {
        marginRight: '5px'
      },
      on: {
        click: () => {
          vm.$emit('on-detail', params)
        }
      }
    }, '详情')
  }
}

export default btns
