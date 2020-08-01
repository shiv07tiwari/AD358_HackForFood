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
      <table class="table table-bordered" style="border: 1px solid gray;">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Complaint ID</th>
            <th scope="col">Location</th>
            <th scope="col">Road ID</th>
            <th scope="col">District</th>
            <th scope="col">Defect Type</th>
            <th scope="col">Reported On</th>
            <th scope="col">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="complaint in complaints" :key="complaint.id">
            <td>{{complaint.id}}</td>
            <td>{{complaint.location}}</td>
            <td>{{complaint.roadid}}</td>
            <td>{{complaint.district}}</td>
            <td>{{complaint.type}}</td>
            <td>{{complaint.reported}}</td>
            <td>{{complaint.status}}</td>
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

export default {
  name: "maintenance-portal",
  components: {},
  methods: {

  },
  mounted() {

  },
  data() {
    return {
      complaints: [
        {
          id: "GJVD355",
          roadid: "SK78",
          location: "Raja Mandir, MG Road",
          district: "Vadodara",
          type: "Pothole",
          reported: "23/3/2020",
          status: "Not Verified",
        },
        {
          id: "GJVD356",
          roadid: "SK78",
          location: "Raja Mandir, MG Road",
          district: "Vadodara",
          type: "Pothole",
          reported: "23/3/2020",
          status: "Not Verified",
        },
        {
          id: "GJVD357",
          roadid: "SK78",
          location: "Raja Mandir, MG Road",
          district: "Vadodara",
          type: "Pothole",
          reported: "23/3/2020",
          status: "Not Verified",
        },
        {
          id: "GJVD358",
          roadid: "SK78",
          location: "Raja Mandir, MG Road",
          district: "Vadodara",
          type: "Pothole",
          reported: "23/3/2020",
          status: "Not Verified",
        },
        {
          id: "GJVD359",
          roadid: "SK78",
          location: "Raja Mandir, MG Road",
          district: "Vadodara",
          type: "Pothole",
          reported: "23/3/2020",
          status: "Not Verified",
        },
        {
          id: "GJVD360",
          roadid: "SK78",
          location: "Raja Mandir, MG Road",
          district: "Vadodara",
          type: "Pothole",
          reported: "23/3/2020",
          status: "Not Verified",
        },
      ],
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
