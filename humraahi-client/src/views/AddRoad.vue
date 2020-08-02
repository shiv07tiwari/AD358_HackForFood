<template>
  <div class="app-add-road" style="display: flex; min-height:100vh;">
    <div class="form" style="flex: 0 0 70%; padding: 2rem 5rem;">
      <h1 style="font-weight: 300;" class="mt-3">Add new road</h1>
      <div class="app-form mt-4" style="width: 70%;">
        <h3 style="font-weight: 300;" class="mt-3 mb-4">Basic Details</h3>
        <form>
          <div class="form-row">
            <div class="form-group col-md-3">
              <label>Road Code</label>
              <input type="text" class="form-control" />
            </div>
            <div class="form-group col-md-9">
              <label>Road Name</label>
              <input type="text" class="form-control" id="inputPassword4" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-6">
              <label>Start Location</label>
              <input type="text" class="form-control" />
            </div>
            <div class="form-group col-md-6">
              <label>End Location</label>
              <input type="text" class="form-control" id="inputPassword4" />
            </div>
          </div>

          <div class="form-group">
            <label for="inputAddress">Road Name</label>
            <input type="text" class="form-control" id="inputAddress" />
          </div>
          <div class="form-row">
            <div class="form-group col-md-3">
              <label for="inputCity">District</label>
              <input type="text" class="form-control" id="inputCity" />
            </div>
            <div class="form-group col-md-4">
              <label for="inputState">Road Category</label>
              <select id="inputState" class="form-control">
                <option
                  v-for="category in categories"
                  :value="category[0]"
                  :key="category[0].toString()"
                >{{ category[1] }}</option>
              </select>
            </div>
          </div>
        </form>
      </div>
      <div class="app-form mt-4" style="width: 70%;">
        <h3 style="font-weight: 300;" class="mt-3 mb-4">Technical Details</h3>
        <form>
          <div class="form-row">
            <div class="form-group col-md-4">
              <label>Road Length (km)</label>
              <input type="text" class="form-control" />
            </div>
            <div class="form-group col-md-4">
              <label>Road Material</label>
              <input type="text" class="form-control" />
            </div>
            <div class="form-group col-md-4">
              <label>Road Depth</label>
              <input type="text" class="form-control" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-4">
              <label>Light Vehicle Density</label>
              <input type="text" class="form-control" />
            </div>
            <div class="form-group col-md-4">
              <label>Medium Vehicle Density</label>
              <input type="text" class="form-control" />
            </div>
            <div class="form-group col-md-4">
              <label>Heavy Vehicle Density</label>
              <input type="text" class="form-control" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-3">
              <label>Active from (month)</label>
              <input type="text" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Active from (year)</label>
              <input type="text" class="form-control" id="inputPassword4" />
            </div>
          </div>
        </form>
      </div>
      <div class="app-form mt-4" style="width: 70%;">
        <h3 style="font-weight: 300;" class="mt-3 mb-4">Other Details</h3>
        <form>
          <div class="form-row">
            <div class="form-group col-md-12">
              <label>Exit Points (split via space)</label>
              <input type="text" class="form-control" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-12">
              <label>Remarks</label>
              <textarea type="text" class="form-control" />
            </div>
          </div>
        </form>
        <button class="mt-3 btn btn-success">Continue</button>
      </div>
    </div>
    <div class="random" style="background-color: #172B4D; flex: 1;"></div>
  </div>
</template>

<script>
import { categories } from "./enums";

export default {
  name: "AddRoad",
  components: {},
  data() {
    return {
      categories: Object.entries(categories),
    };
  },
  mounted() {},
};
</script>

<style lang="scss">
.app-add-road {
  
}
</style>
