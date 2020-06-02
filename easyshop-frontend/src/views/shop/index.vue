<template>
    <div class="div">
      <div>
        <Commodity v-for="commodity in commodity_list" :commodity="commodity"></Commodity>
      </div>
    </div>
</template>
<script>
import Commodity from "@/components/Commodity/Commodity.vue"

export default {
  components: { Commodity },
  name: "Shop",
  data() {
    this.showList();
    return {
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
    }
  }
}
</script>
<style scoped>

</style>
