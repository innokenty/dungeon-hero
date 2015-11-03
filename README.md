# dungeon-hero

This is a role-playing game that you can experience right in your console!
Amazing right? Explore the dungeons, slay the beasts, gain levels and win!
Or die. We all know that's gonna happen sometime...

To launch the game you'll need:
* Git
* Java 8
* Maven

If you're okay with that then do the following to play the game:

1. Checkout the repo
1. Run ```mvn clean package```
1. Run ```java -jar target/dungeon-hero.jar 'Your name here'```
1. Follow the onscreen instructions
1. Have fun

Good luck with not dying!!

## P.S. Spoiler
It looks like that:

* Show the map:
```
> m
.........
.+-----..
.|o     .
.|     ..
.+---+ ..
.|...| ..
.+---+...
.. ......
.........
```

* Show you charachter info
```
> i
Innokenty
===== LEVEL ================
1
===== SKILLS ===============
Strength => 1
Agility => 1
Health => 1
Vision => 5
===== EXPERIENCE ===========
total => 0
next level => 0/2000
===== CALCULATED STATS =====
HP => 22
damage => 1-3
```

* Go to the right:
```
> ddd
.............
..---------..
.|    o 1   .
..      +--..
..---+  |....
.....|  |....
...--+  |....
...... ......
.............
```

* Wow, a monster! Let's slay the beast!
```
> dd
You are now fighting with a savage beast! Oh, my brave hero, please be careful!

Innokenty
HP => 22/22
damage => 1-3
=========== VS ===========
Ant-tiger
HP => 10/10
damage => 1-2
```

* Punch it!
```
> pppp
You punch the beast as hard as you can, you deal 1 damage.
The creature doesn't stand still though and hits you back dealing 1 damage!
You punch the beast as hard as you can, you deal 3 damage.
The creature doesn't stand still though and hits you back dealing 2 damage!
You punch the beast as hard as you can, you deal 1 damage.
The creature doesn't stand still though and hits you back dealing 2 damage!
You punch the beast as hard as you can, you deal 3 damage.
The creature doesn't stand still though and hits you back dealing 1 damage!
> p
You punch the beast as hard as you can, you deal 1 damage.
The creature doesn't stand still though and hits you back dealing 1 damage!
> p
You punch the beast as hard as you can, you deal 1 damage.
The creature doesn't stand still though and hits you back dealing 2 damage!
Oh yeah, you've done it, you slaved the beast! You gain 1177 experience.
```

Unfortunately 1177 exp is not enoght for the level two, so... you'll need more monsters! Good luck, my hero!
