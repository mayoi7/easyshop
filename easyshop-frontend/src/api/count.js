import request from '@/utils/request'

export function getTotalAmount() {
  return request({
    url: '/count/trans/total',
    method: 'get'
  })
}
