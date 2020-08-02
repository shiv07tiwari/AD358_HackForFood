<template>
  <div class="app-maintenance-portal">
    <h1 style="font-weight: 300;">Maintenance Portal</h1>
    <div class="complaints-table">
      <h3 style="font-weight: 300; margin-bottom: 2rem;">Active Complaints</h3>
      <div style="margin-bottom: 2rem;">
        <form class="form-inline">
          <label class="sr-only" for="inlineFormInputName2">Name</label>
          <input
            type="text"
            class="form-control mr-sm-2"
            id="inlineFormInputName2"
            placeholder="Search By Complaint ID"
          />
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

          <button type="submit" class="btn btn-primary ml-2">Filter Complaints</button>
        </form>
      </div>
      <table class="table table-bordered header-fixed" style="border: 1px solid gray;">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Complaint ID</th>
            <th scope="col">Road ID</th>
            <th scope="col">District</th>
            <th scope="col">Defect Type</th>
            <th scope="col">Reported On</th>
            <th scope="col">Remark</th>
            <th scope="col">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="complaint in active" :key="complaint.complaint_id">
            <td>{{ complaint.complaint_id }}</td>
            <td>{{ complaint.road_id }}</td>
            <td>{{ districts[complaint.district] }}</td>
            <td>{{ complaint.defect_type }}</td>
            <td>{{ complaint.reported_month }}/{{ complaint.reported_year }}</td>
            <td>{{ complaint.remark }}</td>
            <td>{{ complaint.is_verified ? "Verified" : "Pending Verification" }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="analytics">
      <h3 style="font-weight: 300; margin-bottom: 3rem;">Analytics</h3>
      <div style="width: 500px;">
        <div style="display: flex;">
          <canvas id="piechart" ref="piechart"></canvas>
          <canvas id="barchart" ref="barchart"></canvas>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import {district} from './enums';

import Chart from "chart.js";

export default {
  name: "maintenance-portal",
  components: {},
  methods: {},
  mounted() {
    this.axios
      .post("complaint", { op: "all_join_kp" })
      .then(({ data }) => {
        console.log(data);
        this.complaints = data;
        this.active = data;
      })
      .catch((err) => {
        console.error(err);
      });

    new Chart(this.$refs.piechart, {
      type: "pie",
      data: {
        labels: ["Pothole", "Sign Boards", "Light", "Other"],
        datasets: [
          {
            label: "2018 Sales",
            data: [23, 5, 12, 20],
            backgroundColor: ["#003f5c", "#58508d", "#ff6361", "#ffa600"],
          },
        ],
      },
    });

    new Chart(this.$refs.barchart, {
      type: "bar",
      data: {
        labels: ["May", "June", "July", "August"],
        datasets: [
          {
            label: "Defects by month",
            data: [76, 90, 80, 5],
            backgroundColor: ["#003f5c", "#58508d", "#ff6361", "#ffa600"],
          },
        ],
      },
    });
  },
  data() {
    return {
      complaints: [],
      active: [],
      districts: district
    };
  },
};
</script>

<style lang="scss">
.app-maintenance-portal {
  padding: 3rem 5rem;

  .complaints-table {
    margin-top: 2rem;
  }

  .analytics {
    margin-top: 2rem;
  }
}
</style>
