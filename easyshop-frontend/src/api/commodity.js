import request from '@/utils/request'

export function listCommodities(page, token) {
  return request({
    url: '/shop/list/' + page,
    methods: 'get'
    // params: data
  })
}

export function addToCart(id, token) {
  return request({
    url: '/shop/cart/' + id,
    methods: 'post'
  })
}
