class Player {
  final String name;
  final int xp;
  String team;

  Player.fromJson(Map<String, dynamic> playerJson)
      : name = playerJson['name'],
        xp = playerJson['xp'],
        team = playerJson['team'];

  void sayHello() {
    print("Hi my name is $name");
  }
}

void main() {
  var apiData = [
    {"name": "nico1", "team": "red", "xp": 0},
    {"name": "nico2", "team": "red", "xp": 0},
    {"name": "nico3", "team": "red", "xp": 0},
  ];

  apiData.forEach((playerJson) {
    var player = Player.fromJson(playerJson);
    player.sayHello();
  });
}
