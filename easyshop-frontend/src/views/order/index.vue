<template>
<div class="order-container">
  <div v-for="order in orders">
    <OrderDetail :order="order"></OrderDetail>
  </div>
</div>
</template>

<script>
import OrderDetail from "@/components/OrderDetail/index.vue"

export default {
  name: "Order",
  components: { OrderDetail },
  data() {
    return {
      orders: [
        {
          id: 1,
          userId: 2,
          commodity: {
            id: 3,
            name: 'aaa',
            creator: 'admin',
            description: 'aaa的详情',
            image: 'D:/Images/image/test_01.png',
            price: 30.5
          },
          price: 20.0,
          quantity: 3,
          total: 60.0,
          createTime: '2019-09-09 12:32:55'
        },
        {
          id: 2,
          userId: 2,
          commodity: {
            id: 3,
            name: 'bbb',
            creator: 'admin',
            description: 'bbb的详情',
            image: 'D:\\Images\\image\\test_01.png',
            price: 30.5
          },
          price: 20.0,
          quantity: 3,
          total: 60.0,
          createTime: '2019-09-09 12:32:55'
        },
        {
          id: 3,
          userId: 2,
          commodity: {
            id: 3,
            creator: 'admin',
            name: 'ccc',
            description: 'ccc的详情',
            image: 'D:\\Images\\image\\test_01.png',
            price: 30.5
          },
          price: 20.0,
          quantity: 3,
          total: 60.0,
          createTime: '2019-09-09 12:32:55'
        },
      ]
    }
  }
}
</script>

<style scoped lang="scss">

</style>
