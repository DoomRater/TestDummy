package boni.dummy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemDummy extends Item {
    public ItemDummy() {
        this.setUnlocalizedName("dummy");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float fx, float fy, float fz) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if(!world.isRemote) {
            switch (side.getIndex())
            {
                case 0: y--; y--; break;
                case 1: y++; break;
                case 2: z--; break;
                case 3: z++; break;
                case 4: x--; break;
                case 5: x++; break;
            }

            Vec3 foo = player.getLookVec();

            EntityDummy entity = new EntityDummy(world);
            entity.setPosition(x + 0.5, y, z + 0.5);
            entity.setPlacementRotation(foo, side.getIndex());
            world.spawnEntityInWorld(entity);

            stack.stackSize--;
        }

        return true;
    }
}
