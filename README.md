# PR Conflict Detector 실전 테스트 랩

작은 주문/할인/재고 예제와 12개의 독립 PR로 열린 PR 간 겹침 탐지를 확인합니다.
모든 실전 PR의 기준 브랜치는 `lab/base`입니다. 테스트할 동안 열어 두고 머지하지 마세요.

| 사례 | 기대 결과 |
|---|---|
| A ↔ B | OrderService 같은 줄의 서로 다른 조건/값 수정 |
| B ↔ C | DiscountPolicy 할인율 수정 |
| A ↔ C | 직접 겹침 없음. A-B-C는 한 연결 묶음 |
| D | A/B와 같은 파일의 독립된 메서드: 겹침 없음 |
| E ↔ F | 파일 이름 변경 + 기존 파일의 동일 줄 수정 |
| G ↔ H | 파일 삭제 + 동일 파일 수정 |
| I ↔ J | 같은 위치에 서로 다른 검사 삽입 |
| K ↔ L | 줄 겹침 없음. 함께 컴파일하면 실패하는 의미적 충돌 사례 |

기존 main 대상 README PR #1/#2도 열려 있다면 총 14개 PR, 6쌍의 겹침, 5개 연결 묶음이 예상됩니다.
현재 분석기는 잠재적 수정 영역 겹침을 찾으며, 의미적/실행 충돌을 확정하지 않습니다.

Java 21만 있으면 각 브랜치에서 아래 명령으로 컴파일·자체 테스트할 수 있습니다.

```powershell
New-Item -ItemType Directory -Force .build | Out-Null
$sources = @(Get-ChildItem src/main/java, src/test/java -Filter *.java -Recurse | ForEach-Object { $_.FullName })
javac -encoding UTF-8 -d .build @sources
java -cp .build demo.ShopSelfTest
```

baseline과 각 PR은 단독 컴파일·자체 테스트를 통과하도록 구성했습니다.
K/L은 각각 통과하지만, K 위에 L을 결합하면 새 배치의 reserve(sku, quantity) 호출이 컴파일되지 않습니다.
