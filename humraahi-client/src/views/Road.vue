<template>
  <div class="app-road">
    <template v-if="!loaded">
      <div
        style="display: flex; width: 100%; justify-content: center; align-items: center; flex-direction: column; height: 70vh;"
      >
        <div class="spinner-border text-info mb-3" role="status">
          <span class="sr-only">Loading...</span>
        </div>
        <h3 style="font-weight: 300;">Loading please wait.</h3>
      </div>
    </template>
    <template v-else>
      <div class="header">
        <h1 style="font-weight: 300; text-align:center;">Road ID: {{road.id}}</h1>
        <h3 style="font-weight: 300; text-align:center;">{{road.name}}</h3>
      </div>
      <div class="details">
        <div class="mapphotos" style="flex: 35% 0 0">
          <div class="map">
            <iframe
              src="https://www.google.com/maps/d/u/0/embed?mid=12cBAhgQUhwfqIuE3mBGX-7mMcpWz8UFt"
              width="550"
              height="360"
            ></iframe>
          </div>
          <div class="images mt-4">
            <h5 style="font-weight: 300; margin-bottom: 1rem;">Images:</h5>
            <img src="@/assets/ne1/p2.jpg" width="550" />
          </div>
        </div>
        <div class="content" style="flex: 1">
          <div id="accordion">
            <div class="card">
              <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                  <button
                    class="btn btn-link accordian-btn"
                    data-toggle="collapse"
                    data-target="#collapseOne"
                    aria-expanded="true"
                    aria-controls="collapseOne"
                  >Construction & Location Details</button>
                </h5>
              </div>

              <div
                id="collapseOne"
                class="collapse show"
                aria-labelledby="headingOne"
                data-parent="#accordion"
              >
                <div class="card-body" style="padding: 2rem; font-size: 1.3rem;">
                  <h4 class="mb-4">Location Details</h4>
                  <div>
                    Start Point: <span class="ml-2">{{ road.start }}</span>
                    <span class="ml-3">End Point: {{ road.end }}</span>
                  </div>
                  <div class="mt-2">
                    Exit Loops:
                    <span v-for="exit in road.exits" :key="exit" class="ml-2">{{exit}}</span>
                  </div>
                  <div class="mt-2">
                    Length:
                    <span class="ml-2">{{road.length}}km</span>
                  </div>
                  <div class="mt-2">
                    Category:
                    <span class="ml-2">{{road.category}}</span>
                  </div>
                  <h4 class="mt-4 mb-4">Construction Details</h4>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="card-header" id="headingTwo">
                <h5 class="mb-0">
                  <button
                    class="btn btn-link collapsed accordian-btn"
                    data-toggle="collapse"
                    data-target="#collapseTwo"
                    aria-expanded="false"
                    aria-controls="collapseTwo"
                  >Technical Details</button>
                </h5>
              </div>
              <div
                id="collapseTwo"
                class="collapse"
                aria-labelledby="headingTwo"
                data-parent="#accordion"
              >
                <div
                  class="card-body"
                >Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
              </div>
            </div>
            <div class="card">
              <div class="card-header" id="headingThree">
                <h5 class="mb-0">
                  <button
                    class="btn btn-link collapsed accordian-btn"
                    data-toggle="collapse"
                    data-target="#collapseThree"
                    aria-expanded="false"
                    aria-controls="collapseThree"
                  >Road Segments</button>
                </h5>
              </div>
              <div
                id="collapseThree"
                class="collapse"
                aria-labelledby="headingThree"
                data-parent="#accordion"
              >
                <div
                  class="card-body"
                >Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
export default {
  name: "road",
  components: {},
  data() {
    return {
      loaded: false,
      roadid: "",
      road: {
        id: "NE-1",
        name: "Ahmadabad - Vadodara Expressway",
        start: "Hatkeshwar, Ahmedabad",
        end: "Vemali, Vadodara",
        exits: ["Nadiad", "Anand"],
        length: 93.1,
        category: "National Expressway"
      },
    };
  },
  mounted() {
    setTimeout(() => {
      this.roadid = this.$route.params.id;
      this.loaded = true;
    }, 800);
  },
};
</script>

<style lang="scss">
.app-road {
  padding: 2rem 5rem;

  .details {
    display: flex;
    margin-top: 4rem;

    .card {
      .card-header {
        background-color: #222;
        padding: 5px 1rem;
      }
    }
  }

  .accordian-btn {
    color: white;

    &:active,
    &:focus {
      outline: none;
      box-shadow: none;
    }
  }
}
</style>
