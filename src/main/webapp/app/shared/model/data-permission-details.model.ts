export interface IDataPermissionDetails {
  id?: number;
  ruleId?: string | null;
  leftBracket?: string | null;
  column?: string | null;
  op?: string | null;
  value?: string | null;
  rightBracket?: string | null;
  ordernum?: number | null;
  logicalRel?: string | null;
}

export class DataPermissionDetails implements IDataPermissionDetails {
  constructor(
    public id?: number,
    public ruleId?: string | null,
    public leftBracket?: string | null,
    public column?: string | null,
    public op?: string | null,
    public value?: string | null,
    public rightBracket?: string | null,
    public ordernum?: number | null,
    public logicalRel?: string | null
  ) {}
}
