package dev.ev1dent.cageCollection.Utilities;

import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Utils {

    public Component formatMM(String s){
        return MiniMessage.miniMessage().deserialize(s).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }
}
