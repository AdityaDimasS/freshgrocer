<?php

use PHPUnit\Framework\TestCase;

class ContinuousTest extends TestCase
{
    private $ordersFile;
    private $seedFile;

    protected function setUp(): void
    {
        $this->ordersFile = __DIR__ . '/../../backend/data/orders.json';
        $this->seedFile   = __DIR__ . '/../../backend/data/orders_seed.json';

        // Reset ke seed data
        copy($this->seedFile, $this->ordersFile);

        echo "\n=== BEFORE ===\n";
        echo file_get_contents($this->ordersFile) . "\n";
    }

    public function testOrdersFileReset()
    {
        // Simulasi transaksi baru
        file_put_contents(
            $this->ordersFile,
            '[{"id":"ORD001"}]'
        );

        echo "\n=== DURING ===\n";
        echo file_get_contents($this->ordersFile) . "\n";

        $content = file_get_contents($this->ordersFile);

        $this->assertStringContainsString(
            'ORD001',
            $content
        );
    }

    protected function tearDown(): void
    {
        // Bersihkan kembali
        copy($this->seedFile, $this->ordersFile);

        echo "\n=== AFTER ===\n";
        echo file_get_contents($this->ordersFile) . "\n";
    }
}