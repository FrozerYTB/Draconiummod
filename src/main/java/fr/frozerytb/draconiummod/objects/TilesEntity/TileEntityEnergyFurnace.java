package fr.frozerytb.draconiummod.objects.TilesEntity;

import fr.frozerytb.draconiummod.objects.items.ItemEnergyBattery;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityEnergyFurnace extends TileEntity implements ITickable {
    private int energy; // Stocker le nombre de points d'énergie

    @Override
    public void tick() {
        // Vérifier si une batterie d'énergie est présente dans le slot spécifique du four
        ItemStack batteryStack = getStackInSlot(0); // Supposons que le slot spécifique soit le premier slot (0)
        if (!batteryStack.isEmpty() && batteryStack.getItem() instanceof ItemEnergyBattery) {
            // Récupérer le nombre de points d'énergie de la batterie
            int batteryEnergy = ((ItemEnergyBattery) batteryStack.getItem()).getEnergy(batteryStack);
            // Utiliser l'énergie pour augmenter la vitesse de cuisson du four
            if (energy > 0) {
                // Implémenter la logique pour augmenter la vitesse de cuisson en fonction du nombre de points d'énergie
            }
        }
    }

    private ItemStack getStackInSlot(int i) {}


    @Override
    public void update() {

    }
}