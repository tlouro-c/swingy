package tc.tlouro_c.swingy.models;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

public class HeroBuilder {

	private int dbId;
	private String name;
	private int spriteNumber;
	private CharacterClass characterClass;
	private int attack;
	private int defense;
	private int maxHP;
	private Level level;
	private Artifact artifact;

	public HeroBuilder name(String name) {
		this.name = name;
		return this;
	}

	public HeroBuilder sprite(int spriteNumber) {
		this.spriteNumber = spriteNumber;
		return this;
	}
	public HeroBuilder characterClass(CharacterClass characterClass) {
		this.characterClass = characterClass;
		return this;
	}
	public HeroBuilder attack(int attack) {
		this.attack = attack;
		return this;
	}
	public HeroBuilder defense(int defense) {
		this.defense = defense;
		return this;
	}
	public HeroBuilder maxHP(int maxHP) {
		this.maxHP = maxHP;
		return this;
	}
	public HeroBuilder level(Level level) {
		this.level = level;
		return this;
	}
	public HeroBuilder artifact(Artifact artifact) {
		this.artifact = artifact;
		return this;
	}
	public HeroBuilder dbId(int dbId) {
		this.dbId = dbId;
		return this;
	}

	public Hero build() throws ConstraintViolationException {
		if (spriteNumber == 0) {
			spriteNumber = 1;
		}
		var newHero = new Hero(name,
								spriteNumber,
								characterClass,
								attack,
								defense,
								maxHP);

		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Hero>> violations = validator.validate(newHero);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		return newHero;
	}

	public Hero buildExistingHero() {
		return new Hero(dbId,
						name, 
						spriteNumber,
						level,
						characterClass,
						attack,
						defense,
						maxHP,
						artifact);
	}
	
}
