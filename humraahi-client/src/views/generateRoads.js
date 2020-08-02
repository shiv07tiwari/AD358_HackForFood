let roads = [];

for (let i = 0; i < 50; i++) {
  let road = {
    id: i + 1,
    name: this.$faker().address.streetName() + " Road",
    start: this.$faker().address.city(),
    end: this.$faker().address.city(),
    district: this.districts[
      Math.floor(Math.random() * this.districts.length)
    ][0],
    category: this.categories[
      Math.floor(Math.random() * this.categories.length)
    ][0],
    zone: this.zones[Math.floor(Math.random() * this.zones.length)][0],
    length: Math.random() * 200,
    material: this.materials[
      Math.floor(Math.random() * this.materials.length)
    ][0],
    depth: Math.random() * 10,
    lvd: Math.floor(Math.random() * 5000),
    mvd: Math.random() * 100,
    hvd: Math.floor(Math.random() * 2000),
    activeMonth: Math.ceil(Math.random() * 12),
    activeYear: 1980 + Math.floor(Math.random() * 40),
    exitPoints: [
      this.$faker().address.city(),
      this.$faker().address.city(),
      this.$faker().address.city(),
      this.$faker().address.city(),
    ],
    remarks: this.$faker().lorem.sentence(),
    rut: 2.2 + Math.random() * 21.8,
    potholes: Math.floor(Math.random() * 20),
    crack: 10 + Math.floor(Math.random() * 35),
    long_crack: 2.5 + Math.random() * 26.5,
    iri: 1.5 + Math.random() * 4.59,
    bridge: Math.floor(Math.random() * 2),
    transport: 1 + Math.ceil(Math.random() * 2),
    monuments: 1 + Math.ceil(Math.random() * 5),
    complaints: Math.ceil(Math.random() * 50),
    priority: 0,
    mountain: Math.floor(Math.random() * 2),
    single_axle_load: 721418.14 + Math.random() * 900000,
    no_shoulder: Math.floor(Math.random() * 2),
    shoulder_width: 2.5 + Math.random() * 5.5,
    average_temperature: 13 + Math.random() * 29,
  };

  road.priority = road.transport + road.monuments + road.complaints / 100;

  delete road.transport;
  delete road.monuments;
  delete road.complaints;

  roads.push(road);
}

console.log(JSON.stringify(roads));
