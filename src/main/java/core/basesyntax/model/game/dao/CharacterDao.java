package core.basesyntax.model.game.dao;

import core.basesyntax.model.game.Bowman;
import core.basesyntax.model.game.Character;
import java.util.List;

public interface CharacterDao {
    Character save (Character character);

    List<? extends Character> findAllByPowerAcs();
}
