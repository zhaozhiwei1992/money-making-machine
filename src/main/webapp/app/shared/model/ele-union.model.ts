export interface IEleUnion {
  id?: number;
  eleCatCode?: string | null;
  eleCatName?: string | null;
  eleCode?: string | null;
  eleName?: string | null;
  parentId?: string | null;
  levelNo?: number | null;
  isLeaf?: boolean | null;
  isEnabled?: boolean | null;
  createTime?: string | null;
  updateTime?: string | null;
}

export class EleUnion implements IEleUnion {
  constructor(
    public id?: number,
    public eleCatCode?: string | null,
    public eleCatName?: string | null,
    public eleCode?: string | null,
    public eleName?: string | null,
    public parentId?: string | null,
    public levelNo?: number | null,
    public isLeaf?: boolean | null,
    public isEnabled?: boolean | null,
    public createTime?: string | null,
    public updateTime?: string | null
  ) {
    this.isLeaf = this.isLeaf ?? false;
    this.isEnabled = this.isEnabled ?? false;
  }
}
