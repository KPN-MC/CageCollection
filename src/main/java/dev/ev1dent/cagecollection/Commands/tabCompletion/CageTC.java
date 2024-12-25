package dev.ev1dent.cagecollection.Commands.tabCompletion;

import dev.ev1dent.cagecollection.utilities.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CageTC implements TabCompleter {

    private final Utils Utils = new Utils();
    private final String[] inputArgs = { "give", "set"};
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

            switch (args.length) {
                case 1: {
                    StringUtil.copyPartialMatches(args[0], Arrays.asList(inputArgs), completions);
                    Collections.sort(completions);
                    return completions;
                }
                case 2: {
                    return null;
                }
                case 3:
                    return Utils.getEntityNames(Utils.getLivingEntities());
            }
        return null;
    }


}
