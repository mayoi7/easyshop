<template>
    <div class="cart">
      <el-table
        class="order"
        ref="multipleTable"
        :data="orders"
        tooltip-effect="dark"
        @selection-change="handleSelection"
        style="width: 100%">

        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          label="商品信息"
          width="350">
          <template slot-scope="scope">
            <img src="@/images/test.png" class="image">
            <div class="name">{{ scope.row.name }}</div>
          </template>
        </el-table-column>
        <el-table-column
          label="单价"
          width="120">
          <template slot-scope="scope">
            ￥{{ scope.row.price }}
          </template>
        </el-table-column>
        <el-table-column
          label="数量"
          width="200">
          <template slot-scope="scope">
            <el-input-number size="small" v-model="scope.row.quantity" :min="1"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column
          label="总价"
          width="200">
          <template slot-scope="scope">
            <span style="color: #f40;font-weight: 700;">
              ￥{{(scope.row.price * scope.row.quantity).toFixed(2)}}
            </span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <a href="javascript:;" class="e-link" type="primary">删除</a>
          </template>
        </el-table-column>
      </el-table>
      <div class="order-submit-bar">
        <div style="float: right;">
          <div class="label" style="display: inline-block">
            已选商品<span class="quantity">{{total_amount}}</span>件
          </div>
          <div class="label" style="margin-left: 80px">
            合计：
            <span class="price" style="font-weight: 700;font-size: 22px;">￥{{total_price}}</span>
          </div>
          <div class="submit">
            <a href="javascript:;" @click="submit" class="submit-link" :class="{'disabled': total_amount === 0}">提交</a>
          </div>
        </div>
        </div>
    </div>
</template>

<script>

export default {
  name: "Cart",
  data() {
    let $cart = this.$store.state.shop.cart;
    for (let i=0; i<$cart.length; i++) {

    }
    return {
      orders: [
        {
          id: 1,
          name: 'aaa',
          image: '@/images/test.png',
          description: 'aaaaaaaaaaa',
          price: 10.5,
          quantity: 2,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 2,
          name: 'bbb',
          image: '@/images/test.png',
          description: 'bbbbbbbbbbb',
          price: 11.5,
          quantity: 0,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 3,
          name: 'ccc',
          image: '@/images/test.png',
          description: 'ccccccccccccc',
          price: 12.5,
          quantity: 3,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 4,
          name: 'ccc',
          image: '@/images/test.png',
          description: 'ccccccccccccc',
          price: 12.5,
          quantity: 3,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 5,
          name: 'ccc',
          image: '@/images/test.png',
          description: 'ccccccccccccc',
          price: 12.5,
          quantity: 3,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 6,
          name: 'ccc',
          image: '@/images/test.png',
          description: 'ccccccccccccc',
          price: 12.5,
          quantity: 3,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 7,
          name: 'ccc',
          image: '@/images/test.png',
          description: 'ccccccccccccc',
          price: 12.5,
          quantity: 3,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 8,
          name: 'ccc',
          image: '@/images/test.png',
          description: 'ccccccccccccc',
          price: 12.5,
          quantity: 3,
          time: '2019-09-09 10:35:12'
        },
        {
          id: 9,
          name: 'ccc',
          image: '@/images/test.png',
          description: 'ccccccccccccc',
          price: 12.5,
          quantity: 3,
          time: '2019-09-09 10:35:12'
        }
      ],
      selection: []
    }
  },
  methods: {
    handleSelection(val) {
      this.selection = val;
      console.log(this.selection);
    },
    submit() {
      console.log(this.selection);
    }
  },
  computed: {
    total_price() {
      let price = 0;
      for (let i = 0; i < this.selection.length; i++) {
        price += this.selection[i].price * this.selection[i].quantity;
      }
      return price;
    },
    total_amount() {
      let amount = 0;
      for (let i = 0; i < this.selection.length; i++) {
        amount += this.selection[i].quantity;
      }
      return amount;
    }
  }
}
</script>

<style lang="scss" scoped>

  .cart {
    .order {
      .image {
        width: 100px;
        height: 100px;
      }

      .name {
        display: inline-block;
        position: relative;
        top: -86px;
        margin: auto 10px;
      }
    }

    .order-submit-bar {
      position: fixed;
      bottom: 0;
      height: 50px;
      width: 70rem;
      background: #e6e6e6;
      z-index: 5;

      .submit {
        display: inline-block;
        float: right;
        width: 120px;
        text-align: center;
        background: #f40;
        color: #fff;
        height: 50px;
        letter-spacing: 3px;
        line-height: normal;

        .submit-link {
          line-height: 50px;
          font-size: 20px;
          width: 100%;
          height: 100%;
          display: block;
        }

        &:hover {
          background: #f22d00;
        }
      }

      .label {
        display: inline-block;
        height: 50px;
        line-height: 50px;
      }

      span.quantity {
        padding: 0 5px;
        color: #f40;
        font-weight: 700;
        font-size: 18px;
        position: relative;
        top: 1px;
      }

      span.price {
        padding: 0 5px;
        color: #f40;
        font-weight: 700;
        font-size: 22px;
        position: relative;
        left: -12px;
        top: 2px;
      }
    }
  }

  a.e-link {
    text-decoration: #1482f0 !important;

    &:hover {
      color: #1482f0;
    }
  }

  a.disabled {
    background: #B0B0B0;
    cursor:not-allowed;
  }
</style>
