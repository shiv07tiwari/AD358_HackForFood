<template>
  <div class="app-upload">
    <h1 style="font-weight: 300;" class="mt-3">Upload Existing Database</h1>
    <div class="mt-4">
      <input id="file-upload" type="file" />
    </div>
    <div class="mt-3">
      <button class="btn btn-primary">Upload CSV</button>
    </div>
    <div class="mt-4" style="font-size: 1.2rem;">
      To upload a CSV to the database, select a CSV File in multipart/form-data format and our format. <br /> The feature will read the data and upload it in our databases. <br />
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
};
</script>

<style lang="scss">
.app-upload {
  padding: 2rem 5rem;
}

.custom-file-upload {
    border: 1px solid #ccc;
    display: inline-block;
    padding: 6px 12px;
    cursor: pointer;
}

</style>
