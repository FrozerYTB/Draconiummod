package fr.frozerytb.draconiummod.optimisations;

import net.minecraft.util.math.BlockPos;

import java.util.LinkedList;
import java.util.Queue;

public class FakeWaterUpdateQueue {
    // Utilisation d'une file d'attente pour lisser la charge des mises à jour de l'eau
    private static final Queue<BlockPos> queue = new LinkedList<>();

    // Ajoute une position dans la file d'attente
    public static void add(BlockPos pos) {
        if (!queue.contains(pos)) {
            queue.offer(pos);
        }
    }

    // Récupère et retire la prochaine position à mettre à jour
    public static BlockPos poll() {
        return queue.poll();
    }

    // Vérifie si la file d'attente est vide
    public static boolean isEmpty() {
        return queue.isEmpty();
    }
}
