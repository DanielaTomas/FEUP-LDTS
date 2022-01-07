# LDTS_G0205 - The Sheep's Escape

## Game Description

The Sheep's Escape is a text-based game where you control a sheep. You have to fill the land with grass so that the sheep hides from the wolves. If you fill 80% of the land you win and if wolves hits you or your trail you die.

Project developed by  Daniela Tomás (up202004946@edu.fc.up.pt), Nuno Penafort (up202008405@edu.fc.up.pt) and Sofia Sousa (up202005932@edu.fc.up.pt) for LDTS 2021-22.

[Full Report](./docs/README.md).

## Implemented Features

- **Buttons** - Functional and interactive buttons.
- **Keyboard Control** - The keyboard inputs are received through the respective events and interpreted according the current game state.
- **Player Control** - The player can move the sheep with the keyboard control.
- **Collision Detections** - Collisions between different objects are verified (Ex: Sheeps and Wolves).
- **Quit** - The key 'q' can be used at any time to quit the game.

## Planned Features

- **Connected Menus** - The user has the capability of browsing through different menus (Ex: Main Menu and Pause).
- **Special Items** - The game will have special items that give temporary abilities to the player.
- **Different types of wolves** - The game will have different types of wolves each type with a different abilitie.
- **Add more tests**

## Design
### General Structure
#### Problem in Context:
Since our game is a GUI our first concern was how the structure would look like.

#### The Pattern:
We used the **_Command Pattern_** that encapsulate a request as an object thereby letting you parameterize clients with different requests.

#### Implementation:
Regarding the implementtaion at the moment we have:

![Structure drawio-2](https://user-images.githubusercontent.com/93272180/148602177-e9e977fc-7890-4c77-88dd-0f9eab03f909.png)

#### Consequences:

### Field
#### Problem in Context:
Since our game is a GUI our first concern was how the structure woul look like.

#### The Pattern:
We used the **_Strategy Pattern_** that defines a family of algorithms, encapsulate each one, and make them interchangeable.

#### Implementation:
Regarding the implementtaion at the moment we have:



#### Consequences:









