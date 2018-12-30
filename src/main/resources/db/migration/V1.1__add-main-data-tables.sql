CREATE TABLE IF NOT EXISTS `team` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `image_link` VARCHAR(384) NOT NULL,
  `formation` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE IF NOT EXISTS `player` (
  `id` VARCHAR(128) NOT NULL,
  `rating` INT(11) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `is_goalkeeper` TINYINT(1) NOT NULL,
  `team_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_player_team_idx` (`team_id` ASC),
  CONSTRAINT `fk_player_team`
  FOREIGN KEY (`team_id`)
  REFERENCES `team` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `war` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `home_goals` INT(11) NOT NULL,
  `away_goals` INT(11) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `team_war` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `team_id` INT NOT NULL,
  `war_id` INT NOT NULL,
  `war_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_war_team1_idx` (`team_id` ASC),
  INDEX `fk_team_war_match1_idx` (`war_id` ASC),
  CONSTRAINT `fk_team_war_team1`
  FOREIGN KEY (`team_id`)
  REFERENCES `team` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_team_war_match1`
  FOREIGN KEY (`war_id`)
  REFERENCES `war` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
