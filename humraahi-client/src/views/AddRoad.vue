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
              <input type="text" class="form-control" v-model="details.road_id" />
            </div>
            <div class="form-group col-md-9">
              <label>Road Name</label>
              <input v-model="details.name" type="text" class="form-control" id="inputPassword4" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-6">
              <label>Start Location</label>
              <input v-model="details.start" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-6">
              <label>End Location</label>
              <input v-model="details.end" type="text" class="form-control" id="inputPassword4" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-4">
              <label for="inputState">District</label>
              <select v-model="details.district" id="inputState" class="form-control">
                <option
                  v-for="district in districts"
                  :value="district[0]"
                  :key="district[0].toString()"
                >{{ district[1] }}</option>
              </select>
            </div>
            <div class="form-group col-md-4">
              <label for="inputState">Road Category</label>
              <select v-model="details.category" id="inputState" class="form-control">
                <option
                  v-for="category in categories"
                  :value="category[0]"
                  :key="category[0].toString()"
                >{{ category[1] }}</option>
              </select>
            </div>
            <div class="form-group col-md-4">
              <label for="inputState">Zone</label>
              <select v-model="details.zone" id="inputState" class="form-control">
                <option
                  v-for="zone in zones"
                  :value="zone[0]"
                  :key="zone[0].toString()"
                >{{ zone[1] }}</option>
              </select>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-3">
              <label>Active from (month)</label>
              <input
                v-model="details.activeMonth"
                type="number"
                min="1"
                max="12"
                class="form-control"
              />
            </div>
            <div class="form-group col-md-3">
              <label>Active from (year)</label>
              <input
                v-model="details.activeYear"
                type="number"
                min="1970"
                max="2020"
                class="form-control"
                id="inputPassword4"
              />
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
              <input v-model="details.length" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-4">
              <label for="inputState">Material Type</label>
              <select v-model="details.material" id="inputState" class="form-control">
                <option
                  v-for="material in materials"
                  :value="material[0]"
                  :key="material[0].toString()"
                >{{ material[1] }}</option>
              </select>
            </div>
            <div class="form-group col-md-4">
              <label>Road Depth</label>
              <input v-model="details.depth" type="text" class="form-control" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-4">
              <label>Light Vehicle Density</label>
              <input v-model="details.lvd" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-4">
              <label>Medium Vehicle Density</label>
              <input v-model="details.mvd" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-4">
              <label>Heavy Vehicle Density</label>
              <input v-model="details.hvd" type="text" class="form-control" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-3">
              <label>IRI (m/km)</label>
              <input v-model="details.iri" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Single Axle Load (msa)</label>
              <input v-model="details.single_axle_load" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Shoulder Width (ft)</label>
              <input v-model="details.shoulder_width" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>No Shoulder</label>
              <input
                v-model="details.no_shoulder"
                type="number"
                min="0"
                max="1"
                class="form-control"
              />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-3">
              <label>Crack (m)</label>
              <input v-model="details.crack" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Potholes</label>
              <input v-model="details.potholes" type="number" min="0" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Rut</label>
              <input v-model="details.rut" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Long Crack</label>
              <input v-model="details.long_crack" type="text" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Bridge</label>
              <input v-model="details.bridge" type="number" min="0" max="1" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Mountain</label>
              <input v-model="details.mountain" type="number" min="0" max="1" class="form-control" />
            </div>
            <div class="form-group col-md-3">
              <label>Average Temperature</label>
              <input v-model="details.average_temperature" type="text" class="form-control" />
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
              <input v-model="details.exitPoints" type="text" class="form-control" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-12">
              <label>Remarks</label>
              <textarea v-model="details.remarks" type="text" class="form-control" />
            </div>
          </div>
        </form>
        <button class="mt-3 btn btn-success" @click="submitRoad">Continue</button>
      </div>
    </div>
    <div class="random" style="background-color: #172B4D; flex: 1;"></div>
  </div>
</template>

<script>
import { categories, zones, district, materials } from "./enums";

export default {
  name: "AddRoad",
  components: {},
  data() {
    return {
      categories: Object.entries(categories),
      zones: Object.entries(zones),
      districts: Object.entries(district),
      materials: Object.entries(materials),
      details: {
        road_id: "",
        name: "",
        start: "",
        end: "",
        district: "",
        category: "",
        zone: "",
        length: 0,
        material: "",
        depth: "",
        lvd: "",
        mvd: "",
        hvd: "",
        activeMonth: 1,
        activeYear: 2020,
        exitPoints: "",
        remarks: "",
        rut: "",
        potholes: 0,
        crack: "",
        long_crack: "",
        iri: "",
        bridge: "",
        mountain: "",
        single_axle_load: "",
        no_shoulder: "",
        shoulder_width: "",
        average_temperature: "",
      },
    };
  },
  methods: {
    submitRoad() {
      console.log(this.details);
    },
  },
  mounted() {},
};
</script>

<style lang="scss">
.app-add-road {
}
</style>
