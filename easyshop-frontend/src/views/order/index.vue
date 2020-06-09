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
    this.loadOrders();
    return {
      orders: []
    }
  },
  methods: {
    loadOrders() {
      this.$store.dispatch('order/getList').then(data => {
        this.orders = data;
      })
    }
  }
}
</script>

<style scoped lang="scss">

</style>
