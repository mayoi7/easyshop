<template>
    <div class="commodities">
      <div>
        <Commodity v-for="commodity in commodity_list" :commodity="commodity"></Commodity>
      </div>
      <div class="total"><em class="price">￥{{total}}</em></div>
    </div>
</template>
<script>
import Commodity from "@/components/Commodity/Commodity.vue"

export default {
  components: { Commodity },
  name: "Shop",
  data() {
    this.showList();
    this.refreshTotalAmount();
    return {
      total: 0,
      commodity_list: []
    }
  },
  methods: {
    showList() {
      let pageData = {
        page: 1
      };
      this.$store.dispatch('shop/list', pageData).then(data => {
        this.commodity_list = data;
      })
    },
    refreshTotalAmount() {
      this.$store.dispatch('count/transTotal').then(data => {
        this.total = data
      })
    }
  }
}
</script>
<style scoped lang="scss">

  .commodities {

    .total {
      z-index: 5;
      position: fixed;
      top: 65px;
      left: 45px;

      .price {
        font-size: 35px;
        font-weight: 900;
        color: #f40;
      }
    }
  }
</style>
