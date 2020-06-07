<template>
  <div class="order">
    <div class="namebar">
      <strong>{{order.createTime}}</strong>
      <div class="order-id" style="display: inline-block; margin-left: 10px;">
        订单号：{{order.id}}
      </div>
    </div>
    <div class="info">
      <img class="commodity-image" src="@/images/test.png" alt="">
      <div class="intro">
        <a href="" class="name">{{order.commodity.name}}</a>
        <div class="description">{{order.commodity.description}}</div>
      </div>
      <div class="price" style="margin-right: 40px;">￥{{order.price}}</div>
      <div class="quantity" style="margin-right: 80px;">{{order.quantity}}</div>
      <div class="total" style="margin-right: 15px;">￥{{order.total}}</div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "OrderDetail",
    props: ["order"]
  }
</script>

<style scoped lang="scss">
.order {
  height: 180px;
  margin: 10px auto;

  .namebar {
    height: 40px;
    padding: 10px;
    background: #f1f1f1;
    font-size: 12px;
  }

  .info {
    height: 140px;
    padding: 10px 10px;

    div {
      display: inline-block;
      vertical-align: top;
    }

    .commodity-image {
      width: 90px;
      height: 90px;
    }

    .intro {
      width: 320px;
      margin: auto 12px;

      .name {
        display: block;
        margin-bottom: 15px;

        &:hover {
          color: #ff4200;
        }
      }
    }

  }
}
</style>
