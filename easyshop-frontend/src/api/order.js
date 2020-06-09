import request from '@/utils/request'

export function placeOrder(order) {
  return request({
    url: '/shop/order/',
    method: 'post',
    order
  })
}

export function placeOrderList(orders) {
  return request({
    url: '/shop/order/list/',
    headers: { 'content-type': 'application/json' },
    method: 'post',
    data: orders
  })
}

export function getOrderList() {
  return request({
    url: '/shop/order/list/',
    method: 'get'
  })
}
