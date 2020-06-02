<template>
  <div class="commodity">
    <el-card class="commodity-card">
      <img src="@/images/test.png" class="image">
      <div class="price">
        <span style="color: #F40">￥</span>
        <strong style="color: #F40;font-weight: 700;">{{commodity.currentPrice}}</strong>
      </div>
      <div class="name">{{commodity.name}}</div>
      <el-button v-if="!state.cart" type="medium" plain class="add" @click="addCart" :loading="loadings.cart_add">添加到购物车</el-button>
      <el-button v-else type="medium" plain disabled class="add">已添加</el-button>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Commodity",
  props: ['commodity'],
  data() {
    return {
      name: 'aa',
      loadings: {
        cart_add: false
      },
      state: {
        cart: false
      }
    }
  },
  methods: {
    addCart() {
      this.loadings.cart_add = true;
      this.$store.dispatch('shop/cart', this.commodity.id).then(msg => {
        this.loadings.cart_add = false;
        this.state.cart = true;
        this.$message({
          message: msg,
          type: 'success'
        });
      })
    }
  },
  computed: {
    discount() {
      return (this.commodity.currentPrice / this.commodity.originalPrice).toFixed(2);
    }
  }
}
</script>

<style lang="scss" scoped>
.commodity {
  display: inline-block;
  margin: 15px 30px;

  .commodity-card {
    width: 300px;
    height: 375px;

    .image {
      width: 250px;
      height: 250px;
    }

    .name {
      margin: 4px auto;
      font-size: 20px;
    }

    .add {
      float: right;
    }
  }
}
</style>
