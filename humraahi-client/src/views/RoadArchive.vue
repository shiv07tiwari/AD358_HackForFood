<template>
  <div class="app-road-archive">
    <h1 style="font-weight: 300; margin-bottom: 2rem;">Road Archive Portal</h1>

    <iframe
      src="https://www.google.com/maps/d/embed?mid=1rd6B6h9ZcbZo2Q8kwcYqaAt-ROuy1UP3"
      width="1740"
      height="480"
    ></iframe>

    <div class="roads-table">
      <h3 style="font-weight: 300; margin-bottom: 2rem;">Roads</h3>
      <div style="margin-bottom: 2rem;">
        <form class="form-inline">
          <label class="sr-only" for="inlineFormInputName2">Name</label>
          <input
            type="text"
            class="form-control mr-sm-2"
            id="inlineFormInputName2"
            placeholder="Search By Road ID"
          />
          <select class="custom-select mr-2" id="inlineFormCustomSelectPref">
            <option selected>District</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
          </select>

          <button type="submit" class="btn btn-primary ml-2">Filter Roads</button>
        </form>
      </div>
      <table class="table table-bordered header-fixed" style="border: 1px solid gray;">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Road ID</th>
            <th scope="col">Road Zone</th>
            <th scope="col">Road Type</th>
            <th scope="col">Districts</th>
            <th scope="col">Start</th>
            <th scope="col">End</th>
            <th scope="col">Length</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="road in roads" :key="road.id">
            <router-link
              tag="td"
              :to="{name: 'road', params: {id: road.id}}"
              style="cursor: pointer; text-decoration: underline;"
            >{{ road.id }}</router-link>
            <td>{{road.zone}}</td>
            <td>{{road.sub_category}}</td>
            <td>{{road.district}}</td>
            <td>{{road.start.substring(1, 10)}}</td>
            <td>{{road.end.substring(1, 10)}}</td>
            <td>{{road.length_of_road}}km</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="analytics">
      <h3 style="font-weight: 300; margin-bottom: 3rem;">Analytics</h3>
    </div>
  </div>
</template>

<script>
import roads from "./roads";

export default {
  name: "road-archive",
  components: {},
  methods: {},
  mounted() {},
  data() {
    return {
      roads: roads,
    };
  },
};
</script>

<style lang="scss">
.app-road-archive {
  padding: 3rem 5rem;

  .roads-table {
    margin-top: 2rem;
  }

  .analytics {
    margin-top: 2rem;
  }
}
</style>
