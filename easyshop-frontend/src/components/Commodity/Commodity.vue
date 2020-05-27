<template>
  <div>
    {{name}} <br/>
    title: {{title}}
  </div>
</template>

<script>
export default {
  name: "Commodity",
  props: ['title'],
  data() {
    return {
      'name': 'aa'
    }
  }
}
</script>

<style scoped>

</style>
