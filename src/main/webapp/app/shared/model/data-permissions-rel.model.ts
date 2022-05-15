export interface IDataPermissionsRel {
  id?: number;
  ruleId?: string | null;
  roleId?: string | null;
  menuId?: string | null;
}

export class DataPermissionsRel implements IDataPermissionsRel {
  constructor(public id?: number, public ruleId?: string | null, public roleId?: string | null, public menuId?: string | null) {}
}
