import Mock from 'mockjs'
import { getTableData, getDragList } from './data'

// 登录相关和获取用户信息
Mock.mock(/\/get_table_data/, getTableData)
Mock.mock(/\/get_drag_list/, getDragList)
Mock.mock(/\/save_error_logger/, 'success')

export default Mock
