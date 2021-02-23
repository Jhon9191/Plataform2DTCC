package Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class NPC1  extends Entity{

	public int movimentacao = 0;
	public int frames = 0, maxFrames = 16, index = 0, maxIndex = 1;
	private BufferedImage npc[];
	private int maskx = 0, masky = 0, maskw = 16, maskh = 16;
	public boolean colisao = false;
	
	public NPC1(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
		npc = new BufferedImage[2];
		
		for (int i = 0; i < 2; i++) {
			npc[i] = Game.spritsheet.getSprite(80 + (16*i), 128, 16, 16);
		}
		
	}
	
	
	public void tick() {
		
		if(movimentacao == 0) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
		
		if(coliding(this.getX(), this.getY())) {
			colisao = true;
		}else {
			colisao = false;
		}
	}
	
	public boolean coliding(int nextx, int nexty) {
		Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Game.entidades.size(); i++) {
			Entity entidade = Game.entidades.get(i);
			if(entidade instanceof Player) {
				Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw,maskh);
				if(player.intersects(solido)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		g.drawImage(npc[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		if(colisao == true) {
			g.drawImage(Entity.pressc, this.getX()-Camera.x, this.getY()-Camera.y - 12, null);			
		}
	}

}
