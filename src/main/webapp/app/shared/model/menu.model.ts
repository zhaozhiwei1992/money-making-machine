export interface IMenu {
  id?: number;
  url?: string | null;
  name?: string | null;
  iconCls?: string | null;
  ordernum?: number | null;
  keepAlive?: boolean | null;
  requireAuth?: boolean | null;
  parentId?: number | null;
  enabled?: boolean | null;
  config?: string | null;
}

export class Menu implements IMenu {
  constructor(
    public id?: number,
    public url?: string | null,
    public name?: string | null,
    public iconCls?: string | null,
    public ordernum?: number | null,
    public keepAlive?: boolean | null,
    public requireAuth?: boolean | null,
    public parentId?: number | null,
    public enabled?: boolean | null,
    public config?: string | null
  ) {
    this.keepAlive = this.keepAlive ?? false;
    this.requireAuth = this.requireAuth ?? false;
    this.enabled = this.enabled ?? false;
  }
}
