import request from '@/utils/request'

export function getCommodity(id, token) {
  return request({
    url: '/shop/commodity/' + id,
    method: 'get'
  })
}

export function listCommodities(page, token) {
  return request({
    url: '/shop/commodity/list/' + page,
    method: 'get'
    // params: data
  })
}

export function addToCart(id, token) {
  return request({
    url: '/shop/cart/' + id,
    method: 'post'
  })
}

export function removeFromCart(id, token) {
  return request({
    url: '/shop/cart/' + id,
    method: 'delete'
  })
}
