import Main from '@/components/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面不会缓存
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/base/login/login.vue')
  },
  {
    path: '/',
    name: '_home',
    redirect: '/base',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/base',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true,
          icon: 'md-base'
        },
        component: () => import('@/view/base/home/home.vue')
      }
    ]
  },
  {
    path: '/system',
    name: '系统管理',
    meta: {
      access: 'system',
      icon: 'ios-book',
      title: '系统管理'
    },
    component: Main,
    children: [
      {
        path: 'tenant',
        name: '客户管理',
        meta: {
          access: 'tenant',
          icon: 'ios-bulb',
          title: '客户管理'
        },
        component: () => import('@/view/admin/tenant/tenant-list.vue')
      },
      {
        path: 'dept',
        name: '部门管理',
        meta: {
          access: 'dept',
          icon: 'md-git-network',
          title: '部门管理'
        },
        component: () => import('@/view/admin/dept/dept-list.vue')
      },
      {
        path: 'user',
        name: '用户管理',
        meta: {
          access: 'user',
          icon: 'ios-contact',
          title: '用户管理'
        },
        component: () => import('@/view/admin/user/user-list.vue')
      },
      {
        path: 'role',
        name: '角色管理',
        meta: {
          access: 'role',
          icon: 'ios-people',
          title: '角色管理'
        },
        component: () => import('@/view/admin/role/role-list.vue')
      },
      {
        path: 'dict',
        name: '字典管理',
        meta: {
          access: 'dict',
          icon: 'md-filing',
          title: '字典管理'
        },
        component: () => import('@/view/admin/dict/dict-list.vue')
      },
      {
        path: 'product',
        name: '产品管理',
        meta: {
          access: 'product',
          icon: 'ios-flower-outline',
          title: '产品管理'
        },
        component: () => import('@/view/admin/product/product-list.vue')
      },
      {
        path: 'menu',
        name: '菜单管理',
        meta: {
          access: 'menu',
          icon: 'ios-infinite',
          title: '菜单管理'
        },
        component: () => import('@/view/admin/menu/menu-list.vue')
      },
      {
        path: 'resource',
        name: '资源管理',
        meta: {
          access: 'resource',
          icon: 'ios-cloud-outline',
          title: '资源管理'
        },
        component: () => import('@/view/admin/resource/resource-list.vue')
      }
    ]
  },
  {
    path: '/config-manager',
    name: '配置管理',
    component: Main,
    meta: {
      icon: 'ios-book',
      title: '配置管理'
    },
    children: [
      {
        path: 'appinfo',
        name: '应用配置',
        meta: {
          title: '应用配置',
          notCache: true,
          icon: 'ios-book'
        },
        component: () => import('@/view/paas/appinfo/config-list.vue')
      },
      {
        path: 'appinfo2',
        name: '应用配置2',
        meta: {
          title: '应用配置2',
          notCache: true,
          icon: 'ios-book'
        },
        component: () => import('@/view/paas/appinfo/config-list.vue')
      }
    ]
  },
  {
    path: '/trade-manager',
    name: '交易管理',
    component: Main,
    meta: {
      icon: 'ios-book',
      title: '交易管理'
    },
    children: [
      {
        path: 'order',
        name: '交易订单',
        meta: {
          title: '交易订单',
          notCache: true,
          icon: 'ios-book'
        },
        component: () => import('@/view/trade/order/payorder-list.vue')
      },
      {
        path: 'account-info',
        name: '账户信息',
        meta: {
          title: '账户信息',
          notCache: true,
          icon: 'ios-book'
        },
        component: () => import('@/view/trade/accountinfo/accountinfo-list.vue')
      },
      {
        path: 'account-flow',
        name: '账户流水',
        meta: {
          title: '账户流水',
          notCache: true,
          icon: 'ios-book'
        },
        component: () => import('@/view/trade/accountflow/accountflow-list.vue')
      }
    ]
  },
  {
    path: '/components',
    name: 'components',
    meta: {
      icon: 'logo-buffer',
      title: 'DEMO'
    },
    component: Main,
    children: [
      {
        path: 'count_to_page',
        name: 'count_to_page',
        meta: {
          icon: 'md-trending-up',
          title: '数字渐变'
        },
        component: () => import('@/view/demo/components/count-to/count-to.vue')
      },
      {
        path: 'drag_list_page',
        name: 'drag_list_page',
        meta: {
          icon: 'ios-infinite',
          title: '拖拽列表'
        },
        component: () => import('@/view/demo/components/drag-list/drag-list.vue')
      },
      {
        path: 'tables_page',
        name: 'tables_page',
        meta: {
          icon: 'md-grid',
          title: '多功能表格'
        },
        component: () => import('@/view/demo/components/tables/tables.vue')
      },
      {
        path: 'split_pane_page',
        name: 'split_pane_page',
        meta: {
          icon: 'md-pause',
          title: '分割窗口'
        },
        component: () => import('@/view/demo/components/split-pane/split-pane.vue')
      },
      {
        path: 'markdown_page',
        name: 'markdown_page',
        meta: {
          icon: 'logo-markdown',
          title: 'Markdown编辑器'
        },
        component: () => import('@/view/demo/components/markdown/markdown.vue')
      },
      {
        path: 'editor_page',
        name: 'editor_page',
        meta: {
          icon: 'ios-create',
          title: '富文本编辑器'
        },
        component: () => import('@/view/demo/components/editor/editor.vue')
      },
      {
        path: 'icons_page',
        name: 'icons_page',
        meta: {
          icon: '_bear',
          title: '自定义图标'
        },
        component: () => import('@/view/demo/components/icons/icons.vue')
      },
      {
        path: '/update',
        name: 'update',
        meta: {
          icon: 'md-cloud-upload',
          title: '数据上传'
        },
        component: parentView,
        children: [
          {
            path: 'update_table_page',
            name: 'update_table_page',
            meta: {
              icon: 'ios-document',
              title: '上传Csv'
            },
            component: () => import('@/view/demo/update/update-table.vue')
          },
          {
            path: 'update_paste_page',
            name: 'update_paste_page',
            meta: {
              icon: 'md-clipboard',
              title: '粘贴表格数据'
            },
            component: () => import('@/view/demo/update/update-paste.vue')
          }
        ]
      },
      {
        path: '/excel',
        name: 'excel',
        meta: {
          icon: 'ios-stats',
          title: 'EXCEL导入导出'
        },
        component: parentView,
        children: [
          {
            path: 'upload-excel',
            name: 'upload-excel',
            meta: {
              icon: 'md-add',
              title: '导入EXCEL'
            },
            component: () => import('@/view/demo/excel/upload-excel.vue')
          },
          {
            path: 'export-excel',
            name: 'export-excel',
            meta: {
              icon: 'md-download',
              title: '导出EXCEL'
            },
            component: () => import('@/view/demo/excel/export-excel.vue')
          }
        ]
      },
      {
        path: '/i18n',
        name: 'i18n',
        meta: {
          hide: true
        },
        component: Main,
        children: [
          {
            path: 'i18n_page',
            name: 'i18n_page',
            meta: {
              icon: 'md-planet',
              title: '国际化'
            },
            component: () => import('@/view/demo/i18n/i18n-page.vue')
          }
        ]
      },
      {
        path: '/error_store',
        name: 'error_store',
        meta: {
          hide: true
        },
        component: Main,
        children: [
          {
            path: 'error_store_page',
            name: 'error_store_page',
            meta: {
              icon: 'ios-bug',
              title: '错误收集'
            },
            component: () => import('@/view/demo/error-store/error-store.vue')
          }
        ]
      },
      {
        path: '/multilevel',
        name: 'multilevel',
        meta: {
          icon: 'md-menu',
          title: '多级菜单'
        },
        component: parentView,
        children: [
          {
            path: 'level_2_1',
            name: 'level_2_1',
            meta: {
              icon: 'md-funnel',
              title: '二级-1'
            },
            component: () => import('@/view/demo/multilevel/level-2-1.vue')
          },
          {
            path: 'level_2_2',
            name: 'level_2_2',
            meta: {
              access: ['super_admin'],
              icon: 'md-funnel',
              showAlways: true,
              title: '二级-2'
            },
            component: parentView,
            children: [
              {
                path: 'level_2_2_1',
                name: 'level_2_2_1',
                meta: {
                  icon: 'md-funnel',
                  title: '三级'
                },
                component: () => import('@/view/demo/multilevel/level-2-2/level-3-1.vue')
              }
            ]
          },
          {
            path: 'level_2_3',
            name: 'level_2_3',
            meta: {
              icon: 'md-funnel',
              title: '二级-3'
            },
            component: () => import('@/view/demo/multilevel/level-2-3.vue')
          },
        ]
      },
      {
        path: 'directive_page',
        name: 'directive_page',
        meta: {
          icon: 'ios-navigate',
          title: '指令'
        },
        component: () => import('@/view/demo/directive/directive.vue')
      },
      {
        path: 'tools_methods_page',
        name: 'tools_methods_page',
        meta: {
          icon: 'ios-hammer',
          title: '工具方法',
          beforeCloseName: 'before_close_normal'
        },
        component: () => import('@/view/demo/tools-methods/tools-methods.vue')
      }
    ]
  },
  {
    path: '/argu',
    name: 'argu',
    meta: {
      hideInMenu: true
    },
    component: Main,
    children: [
      {
        path: 'params/:id',
        name: 'params',
        meta: {
          icon: 'md-flower',
          title: route => `动态路由-${route.params.id}`,
          notCache: true,
          beforeCloseName: 'before_close_normal'
        },
        component: () => import('@/view/demo/argu-page/params.vue')
      },
      {
        path: 'query',
        name: 'query',
        meta: {
          icon: 'md-flower',
          title: route => `带参路由-${route.query.id}`,
          notCache: true
        },
        component: () => import('@/view/demo/argu-page/query.vue')
      }
    ]
  },
  {
    path: '/401',
    name: 'error_401',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/base/error-page/401.vue')
  },
  {
    path: '/500',
    name: 'error_500',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/base/error-page/500.vue')
  },
  {
    path: '*',
    name: 'error_404',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/base/error-page/404.vue')
  },
  {
    path: '/error_logger',
    name: 'error_logger',
    meta: {
      hide: true,
      hideInMenu: true
    },
    component: Main,
    children: [
      {
        path: 'error_logger_page',
        name: 'error_logger_page',
        meta: {
          icon: 'ios-bug',
          title: '错误日志'
        },
        component: () => import('@/view/demo/error-store/error-logger.vue')
      }
    ]
  }
]
